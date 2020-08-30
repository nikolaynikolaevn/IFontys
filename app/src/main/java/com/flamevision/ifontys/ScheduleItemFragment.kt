package com.flamevision.ifontys

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

private const val ARG_CLASSES = "ARG_CLASSES"

class ScheduleItemFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var classesData: ArrayList<CourseClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_CLASSES) }?.apply {
            classesData = this.getParcelableArrayList<CourseClass>(ARG_CLASSES) as ArrayList<CourseClass>
        }
        viewManager = LinearLayoutManager(activity)
        viewAdapter = MyScheduleItemRecyclerViewAdapter(classesData)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_schedule_day_classes).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(classes: ArrayList<CourseClass>) =
            ScheduleItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_CLASSES, classes)
                }
            }
    }

}
