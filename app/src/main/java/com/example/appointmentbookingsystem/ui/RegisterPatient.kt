package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appointmentbookingsystem.R
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.PatientDao
import com.example.appointmentbookingsystem.database.entity.Patient
import com.example.appointmentbookingsystem.databinding.ActivityMainBinding
import com.example.appointmentbookingsystem.databinding.ActivityRegisterPatientBinding

// Assigning it to Alex
//Make UI to get register patient and call add patient -> create UI add method in Dao
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
        binding.btnAddPatient.setOnClickListener {
            val name = binding.edPatientName.text.toString()
            val mobileno = binding.edPatientMobile.text.toString()
            val gender = binding.edGender.text.toString()
            val address = binding.edLocation.text.toString()
            val email = binding.edEmail.text.toString()
            val reason = binding.edPatientReason.text.toString()
            patientDao.addPatient(
                Patient(
                    "",
                    name,
                    email,
                    mobileno,
                    gender,
                    address,
                    reason
                )
            )
        }
    }
}