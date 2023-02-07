package com.example.appointmentbookingsystem.dataclasses

import com.example.appointmentbookingsystem.database.Constant
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.database.entity.Patient
import java.time.LocalDateTime

data class UpcomingSchedules(
    val doctorData: Doctor,
    val patientData: Patient,
    val appointmentType: Constant.AppointmentType,
    val appointedTime: LocalDateTime,
    val notes: String
) {

}
