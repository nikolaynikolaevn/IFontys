package com.flamevision.ifontys

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_TOKEN = "ARG_TOKEN"

class ScheduleFragment : Fragment() {

    private lateinit var pagerAdapter: SchedulePagerAdapter
    private lateinit var pager: ViewPager
    private lateinit var tabs: TabLayout

    private lateinit var classes: Array<ArrayList<CourseClass>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_TOKEN) }?.apply {
            FetchSchedule().execute(this.getString(ARG_TOKEN))
        }
        pager = view.findViewById(R.id.schedule_pager)
        tabs = view.findViewById(R.id.schedule_tabs)
    }

    companion object {
        @JvmStatic
        fun newInstance(token: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TOKEN, token)
                }
            }
    }

    inner class FetchSchedule : AsyncTask<String, Void, Array<ArrayList<CourseClass>>>() {

        val JsonAPIDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val originalDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formattedDateFormat = SimpleDateFormat("dd-MM")
        val formattedTimeFormat = SimpleDateFormat("HH:mm")

        private fun parseClasses(classesJSON: JSONArray): ArrayList<CourseClass> {

            val parsedClasses: ArrayList<CourseClass> = ArrayList()
            for (i in 0 until classesJSON.length()) {
                val courseClassJSON = classesJSON.getJSONObject(i)
                parsedClasses.add(CourseClass(
                        courseClassJSON.getString("subject"),
                        courseClassJSON.getString("description"),
                        formattedDateFormat.format(originalDateFormat.parse(courseClassJSON.getString("start"))), // dd-MM (date)
                        formattedTimeFormat.format(originalDateFormat.parse(courseClassJSON.getString("start"))), // HH:mm
                        formattedTimeFormat.format(originalDateFormat.parse(courseClassJSON.getString("end"))), // HH:mm
                        courseClassJSON.getString("teacherAbbreviation"),
                        courseClassJSON.getString("room")
                    ))
            }
            return parsedClasses
        }

        private fun getClassesOnDate(classes: ArrayList<CourseClass>, date: String): ArrayList<CourseClass> {
            val classesOnDate: ArrayList<CourseClass> = ArrayList()

            for (i in classes) {
                if (i.classDate == formattedDateFormat.format(JsonAPIDateFormat.parse(date))) classesOnDate.add(i)
            }
            return classesOnDate
        }

        private fun getClassesGroupedByDate(classes: ArrayList<CourseClass>, monday: Calendar): Array<ArrayList<CourseClass>> {
            val groupedClasses = Array(5) { ArrayList<CourseClass>() }
            val date: Calendar = monday.clone() as Calendar

            for (i in 0..4) {
                val dateString = JsonAPIDateFormat.format(date.time)
                groupedClasses[i] = getClassesOnDate(classes, dateString)
                date.add(Calendar.DATE, 1);
            }
            return groupedClasses
        }

        override fun doInBackground(vararg params: String?): Array<ArrayList<CourseClass>> {
            val date = Calendar.getInstance() // start from today
            // Get next Monday
            while (date.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                date.add(Calendar.DATE, 1);
            }
            val nextMondayString = JsonAPIDateFormat.format(date.time)

            val url = URL("https://api.fhict.nl/schedule/me?days=5&start=$nextMondayString")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("Authorization", "Bearer " + params[0])
            connection.connect()
            val scn = Scanner(connection.inputStream)
            val result = scn.useDelimiter("\\Z").next()

            val jsonObject = JSONObject(result)
            val jsonClassesArray: JSONArray = jsonObject.getJSONArray("data")

            val parsedClasses: ArrayList<CourseClass> = parseClasses(jsonClassesArray)
            return getClassesGroupedByDate(parsedClasses, date)
        }

        override fun onPostExecute(result: Array<ArrayList<CourseClass>>) {
            super.onPostExecute(result)
            classes = result
            pagerAdapter = SchedulePagerAdapter(childFragmentManager, classes)
            pager.adapter = pagerAdapter
            tabs.setupWithViewPager(pager)
            //val scheduleItemFragment = ScheduleItemFragment()
            //scheduleItemFragment.setDataSet(classes)
            //childFragmentManager.beginTransaction().replace(R.id.fragment_container, scheduleItemFragment).commit()
        }

    }

}
