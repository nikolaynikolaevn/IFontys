package com.flamevision.ifontys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

private const val ARG_CLASSES = "ARG_CLASSES"

class ScheduleDayFragment : Fragment() {
    private var classes: Serializable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_CLASSES) }?.apply {
            //val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_schedule_day_classes)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(classes: Serializable) =
            ScheduleDayFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLASSES, classes)
                }
            }
    }
}
