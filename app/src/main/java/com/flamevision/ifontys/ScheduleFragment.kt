package com.flamevision.ifontys

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

private const val ARG_TOKEN = "ARG_TOKEN"

class ScheduleFragment : Fragment() {

    private lateinit var pagerAdapter: SchedulePagerAdapter
    private lateinit var pager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pagerAdapter = SchedulePagerAdapter(childFragmentManager)
        pager = view.findViewById(R.id.schedule_pager)
        pager.adapter = pagerAdapter
        tabs = view.findViewById(R.id.schedule_tabs)
        tabs.setupWithViewPager(pager)
        arguments?.takeIf { it.containsKey(ARG_TOKEN) }?.apply {
            FetchSchedule().execute(this.getString(ARG_TOKEN))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(token: String) =
            ScheduleDayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TOKEN, token)
                }
            }
    }

    private class FetchSchedule : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            val url = URL("https://api.fhict.nl/schedule/me")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("Authorization", "Bearer " + params[0])
            connection.connect()
            val scn = Scanner(connection.inputStream)
            return scn.useDelimiter("\\Z").next()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

    }

}
