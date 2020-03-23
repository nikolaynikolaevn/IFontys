package com.flamevision.ifontys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        val schedule: Button = findViewById(R.id.schedule)

        schedule.setOnClickListener {
            val scheduleIntent = Intent(this, ScheduleActivity::class.java)
            startActivity(scheduleIntent)
        }
    }
}
