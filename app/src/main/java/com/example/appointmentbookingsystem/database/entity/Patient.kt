package com.example.appointmentbookingsystem.database.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Patient(
    val id: Long,
    val name: String,
    val email: String,
    val mobileNo: String,
    val gender: String,
    val address: String,
    val reason: String,
    val age: Int? = 0
) : Parcelable
