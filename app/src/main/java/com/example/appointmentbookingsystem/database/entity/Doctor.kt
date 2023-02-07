package com.example.appointmentbookingsystem.database.entity

data class Doctor(
    val id: Long,
    val name: String,
    val mobileNo: String,
    val gender: String,
    val specialization: String,
    val location: String,
    val rating: Int,
    val experience: Int,
    val about: String,
    val email: String,
)
