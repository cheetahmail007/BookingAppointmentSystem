package com.example.appointmentbookingsystem.database.dao

import android.content.ContentValues
import android.database.Cursor
import com.example.appointmentbookingsystem.database.Constant
import com.example.appointmentbookingsystem.database.Constant.DOCTOR_TABLE
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.database.entity.Patient

class DoctorDao(private val dbHelper: DbHelper) {
    private var database = dbHelper

    fun addDoctor(doctor: Doctor): Long {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", doctor.name)
            put("mobileNo", doctor.mobileNo)
            put("gender", doctor.gender)
            put("specialization", doctor.specialization)
            put("location", doctor.location)
            put("rating", doctor.rating)
            put("experience", doctor.experience)
            put("about", doctor.about)
            put("email", doctor.email)
        }

        return database.writableDatabase.insert(DOCTOR_TABLE, null, contentValues)
    }

    fun getAllDoctor(): ArrayList<Doctor> {
        val doctorList = ArrayList<Doctor>()

        val cursor: Cursor = database.readableDatabase.query("doctor", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val doctorId = cursor.getLong(0)
            val name = cursor.getString(1)
            val mobileNo = cursor.getString(2)
            val doctor = Doctor(doctorId,name,mobileNo,"","","",0,0,"0","")
            doctorList.add(doctor)
        }
        return doctorList
    }

    fun deleteDoctor(patientId: Long): Boolean {
        val numOfRowDeleted: Int = database.writableDatabase.delete("doctor", "doctorId=$patientId", null)
        return numOfRowDeleted == 1
    }

    fun updateDoctor(patient: Patient): Boolean {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", patient.name)
            put("mobileNo", patient.mobileNo)
        }

        val numOfRowDeleted: Int =
            database.writableDatabase.update("doctor", contentValues, "doctorId=${patient.id}", null)
        return numOfRowDeleted == 1
    }
}