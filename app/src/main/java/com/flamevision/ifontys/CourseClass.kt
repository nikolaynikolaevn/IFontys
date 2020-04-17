package com.flamevision.ifontys

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseClass (val className: String, val classDescription : String, val classDate : String,
                        val startTime: String, val endTime: String, val teacherAbr: String, val place: String) :
    Parcelable {
    val classInfo = "$classDescription | $place | $startTime | $endTime | $teacherAbr"
}