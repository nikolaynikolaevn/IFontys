package com.flamevision.ifontys

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

private const val ARG_PEOPLE = "ARG_PEOPLE"

class PersonItemFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var peopleData: ArrayList<Person>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_PEOPLE) }?.apply {
            peopleData = this.getParcelableArrayList<CourseClass>(ARG_PEOPLE) as ArrayList<Person>
        }
        viewManager = LinearLayoutManager(activity)
        viewAdapter = PersonItemRecyclerViewAdapter(peopleData)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_people).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(people: ArrayList<Person>) =
            PersonItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PEOPLE, people)
                }
            }
    }

}
