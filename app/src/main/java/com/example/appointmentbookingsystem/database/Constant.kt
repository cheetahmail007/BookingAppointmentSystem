package com.example.appointmentbookingsystem.database

object Constant {
    const val DATABASE_NAME = "appointment-db"
    const val DATABASE_VERSION = 1
    const val DOCTOR_TABLE = "doctor"
    const val PATIENT_TABLE = "patient"
    const val APPOINTMENT_TABLE = "appointment"

    val CREATE_DOCTOR_TABLE = """CREATE TABLE $DOCTOR_TABLE (
         doctorId INTEGER PRIMARY KEY AUTOINCREMENT,
         name TEXT,
          mobileNo TEXT,
          gender TEXT,
          specialization TEXT,
          location TEXT,
          rating INTEGER,
          experience INTEGER,
          about TEXT,
          email TEXT)
          """.trimIndent()

    val CREATE_PATIENT_TABLE = """CREATE TABLE $PATIENT_TABLE (
         patientId INTEGER PRIMARY KEY AUTOINCREMENT,
         name TEXT,
          mobileNo TEXT,
          gender TEXT,
          location TEXT,
          email TEXT)
          """.trimIndent()

    val CREATE_APPOINTMENT_TABLE = """CREATE TABLE appointment (
         aptNo INTEGER PRIMARY KEY AUTOINCREMENT,
         doctorId INTEGER,
          patientId INTEGER,
          aptDateTime INTEGER)
          """.trimIndent()

}