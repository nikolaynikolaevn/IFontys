package com.flamevision.ifontys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

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
    }
}
