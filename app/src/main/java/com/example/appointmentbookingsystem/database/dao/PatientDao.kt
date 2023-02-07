package com.example.appointmentbookingsystem.database.dao

import android.content.ContentValues
import android.database.Cursor
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.entity.Patient

class PatientDao(dbHelper: DbHelper) {
    private var db: DbHelper = dbHelper

    fun addPatient(patient: Patient): Long {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", patient.name)
            put("mobileNo", patient.mobileNo)
            put("gender", patient.gender)
            put("location", patient.address)
            put("email", patient.email)
            put("reason", patient.reason)
            put("age", patient.age)
        }

        return db.writableDatabase.insert("patient", null, contentValues)
    }

    fun getAllPatient(): ArrayList<Patient> {
        val patientList = ArrayList<Patient>()

        val cursor: Cursor =
            db.readableDatabase.query("patient", null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val patientId = cursor.getLong(0)
            val name = cursor.getString(1)
            val gender = cursor.getString(2)
            val mobileNo = cursor.getString(3)
            val reason = cursor.getString(5)
            val patient = Patient(patientId, name, gender, mobileNo, gender, reason, "")
            patientList.add(patient)
        }
        return patientList
    }

    fun deletePatient(patientId: Long): Boolean {
        val numOfRowDeleted: Int =
            db.writableDatabase.delete("patient", "patientId=$patientId", null)
        return numOfRowDeleted == 1
    }

    fun updatePatient(patient: Patient): Boolean {
        val contentValues = ContentValues()
        contentValues.apply {
            put("name", patient.name)
            put("gender", patient.gender)
            put("mobileNo", patient.mobileNo)
        }

        val numOfRowDeleted: Int =
            db.writableDatabase.update("patient", contentValues, "patientId=${patient.id}", null)
        return numOfRowDeleted == 1
    }
}
