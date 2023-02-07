package com.example.appointmentbookingsystem.database.entity

data class Appointment(
    val appNo: Long,
    val doctorId: Long,
    val patientId: Long,
    val aptDateTime: Long
)
