package com.example.appointmentbookingsystem.database.entity

data class Patient(
    val id: String,
    val name: String,
    val email : String,
    val mobileNo: String,
    val gender: String,
    val reason:String
)
