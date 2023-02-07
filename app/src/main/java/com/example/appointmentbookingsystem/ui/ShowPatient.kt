package com.example.appointmentbookingsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.PatientDao
import com.example.appointmentbookingsystem.database.entity.Patient
import com.example.appointmentbookingsystem.databinding.ActivityShowPatientBinding
import com.example.appointmentbookingsystem.adapters.PatientAdapter

class ShowPatient : AppCompatActivity() {

    private lateinit var binding: ActivityShowPatientBinding
    private lateinit var dbHelper: PatientDao
    private lateinit var patientAdapter: PatientAdapter
    private lateinit var patient: ArrayList<Patient>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = PatientDao(DbHelper(this))
    }

    override fun onResume() {
        super.onResume()
        patient = dbHelper.getAllPatient()

        setUpView()
    }

    private fun setUpView() {
        patientAdapter = PatientAdapter(this, patient)
        binding.patientRecyclerView.adapter = patientAdapter
        binding.patientRecyclerView.layoutManager =
            LinearLayoutManager(this)

        patientAdapter.setOnPatientSelectedListener { patient:Patient, pos ->
            val data = Intent()
            data.putExtra("patient", patient)
            setResult(RESULT_OK, data)
            finish()
        }
    }
}