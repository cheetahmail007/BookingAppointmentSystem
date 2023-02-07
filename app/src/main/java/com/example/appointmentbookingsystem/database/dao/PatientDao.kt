package com.example.appointmentbookingsystem.database.dao

import android.content.ContentValues
import com.example.appointmentbookingsystem.database.Constant
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.entity.Patient

class PatientDao(dbHelper: DbHelper) {
    private var db: DbHelper = dbHelper

    fun addPatient(patient: Patient) {
        val contentValue = ContentValues().apply {
            patient.apply {
                put(Constant.PATIENT_TABLE,id)
            }
        }
        db.writableDatabase.apply {
            insert(Constant.PATIENT_TABLE,null,contentValue)
        }
    }
}