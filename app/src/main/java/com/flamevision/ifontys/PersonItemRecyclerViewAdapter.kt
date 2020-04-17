package com.flamevision.ifontys

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PersonItemRecyclerViewAdapter(private val mValues: ArrayList<Person>) :
    RecyclerView.Adapter<PersonItemRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.textView_name)
        val info = itemView.findViewById<TextView>(R.id.textView_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_person_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = mValues[position]
        holder.name.text = person.name
        holder.info.text = person.info
    }

    override fun getItemCount() = mValues.size
}
