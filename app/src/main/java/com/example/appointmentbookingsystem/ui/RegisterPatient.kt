package com.example.appointmentbookingsystem.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.PatientDao
import com.example.appointmentbookingsystem.database.entity.Patient
import com.example.appointmentbookingsystem.databinding.ActivityRegisterPatientBinding

class RegisterPatient : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPatientBinding
    private lateinit var dbHelper: DbHelper
    private lateinit var patientDao: PatientDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()
        initView()
    }

    private fun initDatabase() {
        dbHelper = DbHelper(this.applicationContext)
        patientDao = PatientDao(dbHelper)
    }

    private fun initView() {
        binding.apply {
            btnAddPatient.setOnClickListener {
                val name = edPatientName.text.toString()
                val mobileno = edPatientMobile.text.toString()
                val gender = edGender.text.toString()
                val address = edLocation.text.toString()
                val email = edEmail.text.toString()
                val reason = edPatientReason.text.toString()
                if (patientDao.addPatient(
                        Patient(
                            0,
                            name,
                            email,
                            mobileno,
                            gender,
                            address,
                            reason
                        )
                    ) > 0
                ) {
                    finish()
                } else {
                    Toast.makeText(this@RegisterPatient, "Bad input", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}