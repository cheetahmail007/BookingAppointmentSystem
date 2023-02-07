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
    }
    private fun initView() {
        binding.edPatientName.setOnClickListener {
            patientDao.addPatient(Patient(
                "1",
                "Benjamin",
                "benjamin@gmail.com",
                "9256731234",
                "Male",
                "Abdominal pain"
            ))
        }
    }
}