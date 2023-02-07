package com.example.appointmentbookingsystem.database.dao

import android.content.ContentValues
import com.example.appointmentbookingsystem.database.Constant
import com.example.appointmentbookingsystem.database.Constant.DOCTOR_TABLE
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.entity.Doctor

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

}