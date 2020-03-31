package com.flamevision.ifontys

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SchedulePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 5

    override fun getItem(i: Int): Fragment {
        val fragment = ScheduleDayFragment()
        fragment.arguments = Bundle().apply {
            putSerializable("ARG_CLASSES", null)
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Mon"
            1 -> "Tue"
            2 -> "Wed"
            3 -> "Thu"
            else -> "Fri"
        }
    }
}