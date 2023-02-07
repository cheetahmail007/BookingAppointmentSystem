package com.example.appointmentbookingsystem.database.entity

data class AppointmentDetails(
    val appNo: Long,
    val doctorId: Long,
    val doctorName: String,
    val doctorMobileNo: String,
    val patientId: Long,
    val patientName: String,
    val patientMobileNo: String,
    val aptDateTime: Long
)
