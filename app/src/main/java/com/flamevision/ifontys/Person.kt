package com.flamevision.ifontys

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person (val name: String, val email : String, val office : String) : Parcelable {
    val info = "$email | $office"
}