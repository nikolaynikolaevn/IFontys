package com.flamevision.ifontys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val people: Button = findViewById(R.id.people)

        people.setOnClickListener {

        }
    }
}