package com.flamevision.ifontys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, TokenFragment.OnFragmentInteractionListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.fragment_title)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, TokenFragment()).commit()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment: Fragment

        when (item.itemId) {
            R.id.nav_people -> {
                fragment = PeopleFragment()
                title.text = getString(R.string.people)
            }
            else -> {
                fragment = ScheduleFragment()
                title.text = getString(R.string.schedule)
            }
        }
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(token: String?) {
        val scheduleFragment = ScheduleFragment()
        scheduleFragment.arguments = Bundle().apply {
            putString("ARG_TOKEN", token)
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, scheduleFragment).commit() // Default fragment after login
        title.text = getString(R.string.schedule)
    }
}
