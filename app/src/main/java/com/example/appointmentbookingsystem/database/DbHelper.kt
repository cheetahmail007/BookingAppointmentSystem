package com.example.appointmentbookingsystem.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appointmentbookingsystem.database.Constant.APPOINTMENT_TABLE
import com.example.appointmentbookingsystem.database.Constant.CREATE_APPOINTMENT_TABLE
import com.example.appointmentbookingsystem.database.Constant.CREATE_DOCTOR_TABLE
import com.example.appointmentbookingsystem.database.Constant.CREATE_PATIENT_TABLE
import com.example.appointmentbookingsystem.database.Constant.DATABASE_NAME
import com.example.appointmentbookingsystem.database.Constant.DATABASE_VERSION
import com.example.appointmentbookingsystem.database.Constant.DOCTOR_TABLE
import com.example.appointmentbookingsystem.database.Constant.PATIENT_TABLE

class DbHelper(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_DOCTOR_TABLE)
        db?.execSQL(CREATE_PATIENT_TABLE)
        db?.execSQL(CREATE_APPOINTMENT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /*db?.execSQL("DROP Table if exists $DOCTOR_TABLE")
        db?.execSQL("DROP Table if exists $PATIENT_TABLE")
        db?.execSQL("DROP Table if exists $APPOINTMENT_TABLE")*/

        if (oldVersion == 1 && newVersion == 2) {
            db?.execSQL("Alter TABLE patient ADD COLUMN AGE INTEGER DEFAULT 0")
        }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }
}