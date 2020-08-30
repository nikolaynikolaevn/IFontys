package com.flamevision.ifontys

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyScheduleItemRecyclerViewAdapter(private val mValues: ArrayList<CourseClass>) :
    RecyclerView.Adapter<MyScheduleItemRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val className = itemView.findViewById<TextView>(R.id.textView_className)
        val classInfo = itemView.findViewById<TextView>(R.id.textView_classInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_schedule_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courseClass = mValues[position]
        holder.className.text = courseClass.className
        holder.classInfo.text = courseClass.classInfo
    }

    override fun getItemCount() = mValues.size
}
