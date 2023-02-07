package com.example.appointmentbookingsystem.database.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.entity.Appointment
import com.example.appointmentbookingsystem.database.entity.AppointmentDetails
import java.sql.SQLException

class AppointmentDao(private val context: Context) {
    private val db = DbHelper(context).writableDatabase

    fun bookAppointment(appointment: Appointment): Long {
        val contentValues = ContentValues()
        contentValues.apply {
            put("doctorId", appointment.doctorId)
            put("patientId", appointment.patientId)
            put("aptDateTime", appointment.aptDateTime)
        }
        return db.insert("appointment", null, contentValues)
    }

    @SuppressLint("Range")
    fun getAllBookedAppointments(): ArrayList<AppointmentDetails> {
        val list = ArrayList<AppointmentDetails>()

        val query = """
            SELECT aptNo, patient.patientId, patient.name as pName, patient.mobileNo as pMobileNo,
            doctor.doctorId, doctor.name as dName, doctor.mobileNo as dMobileNo,
            aptDateTime FROM patient, doctor, appointment
            WHERE patient.patientId = appointment.patientId
            AND appointment.doctorId = doctor.doctorId
                    """.trimIndent()

        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val aptNo = cursor.getLong(cursor.getColumnIndex("aptNo"))
            val patientId = cursor.getLong(cursor.getColumnIndex("patientId"))
            val pMobileNo = cursor.getString(cursor.getColumnIndex("pMobileNo"))
            val pName = cursor.getString(cursor.getColumnIndex("pName"))
            val doctorId = cursor.getLong(cursor.getColumnIndex("doctorId"))
            val dName = cursor.getString(cursor.getColumnIndex("dName"))
            val dMobileNo = cursor.getString(cursor.getColumnIndex("dMobileNo"))
            val aptDateTime = cursor.getLong(cursor.getColumnIndex("aptDateTime"))
            val aptDetails = AppointmentDetails(
                aptNo,
                doctorId,
                dName,
                dMobileNo,
                patientId,
                pName,
                pMobileNo,
                aptDateTime
            )
            list.add(aptDetails)
        }
        return list
    }

    fun deleteAppointment(patientId: Long): Boolean {
        val numOfRowDeleted: Int = db.delete("appointment", "doctorId=$patientId", null)
        return numOfRowDeleted == 1
    }

    fun confirmBooking(appointment: Appointment) {
        try {
            db.beginTransaction()

            bookAppointment(appointment)
            updateDoctor()
            updatePatient()

            db.setTransactionSuccessful()
        } catch (e: SQLException) {
            db.endTransaction()
        }
    }

    fun updatePatient() {
        //send notification

    }

    fun updateDoctor() {
        //
    }
}