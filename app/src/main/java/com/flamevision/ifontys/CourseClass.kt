package com.flamevision.ifontys

import java.util.Date

data class CourseClass (val className: String, val startTime: Date, val endTime: Date, val teacherName: String, val place: String) {
    val classInfo = "$place | $startTime | $endTime | $teacherName"
}