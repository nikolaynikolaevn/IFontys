package com.flamevision.ifontys

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_TOKEN = "ARG_TOKEN"

class PeopleFragment : Fragment() {
    private lateinit var people: ArrayList<Person>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_TOKEN) }?.apply {
            FetchPeople().execute(this.getString(ARG_TOKEN))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(token: String) =
            PeopleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TOKEN, token)
                }
            }
    }

    inner class FetchPeople : AsyncTask<String, Void, ArrayList<Person>>() {

        private fun parsePeople(peopleJSON: JSONArray): ArrayList<Person> {

            val parsedPeople: ArrayList<Person> = ArrayList()
            for (i in 0 until peopleJSON.length()) {
                val courseClassJSON = peopleJSON.getJSONObject(i)
                parsedPeople.add(Person(
                    courseClassJSON.getString("displayName"),
                    courseClassJSON.getString("mail"),
                    courseClassJSON.getString("office")
                ))
            }
            return parsedPeople
        }

        override fun doInBackground(vararg params: String?): ArrayList<Person> {
            val url = URL("https://api.fhict.nl/people")
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("Authorization", "Bearer " + params[0])
            connection.connect()
            val scn = Scanner(connection.inputStream)
            val result = scn.useDelimiter("\\Z").next()

            val jsonArray = JSONArray(result)
            return parsePeople(jsonArray)
        }

        override fun onPostExecute(result: ArrayList<Person>) {
            super.onPostExecute(result)
            people = result
        }

    }

}
