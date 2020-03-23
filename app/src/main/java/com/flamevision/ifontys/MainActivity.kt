package com.flamevision.ifontys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schedule: Button = findViewById(R.id.main_schedule)
        val people: Button = findViewById(R.id.main_people)
        schedule.setOnClickListener {
            val scheduleIntent = Intent(this, ScheduleActivity::class.java)
            startActivity(scheduleIntent)

        }

        people.setOnClickListener {
            val peopleIntent = Intent(this, PeopleActivity::class.java)
            startActivity(peopleIntent)
        }
    }
}
