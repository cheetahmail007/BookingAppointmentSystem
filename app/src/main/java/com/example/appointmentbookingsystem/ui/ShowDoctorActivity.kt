package com.example.appointmentbookingsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appointmentbookingsystem.adapters.DoctorAdapter
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.DoctorDao
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.databinding.ActivityShowDoctorBinding


class ShowDoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowDoctorBinding
    private lateinit var doctorDao: DoctorDao
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var doctors: ArrayList<Doctor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        doctorDao = DoctorDao(DbHelper(this))
    }

    override fun onResume() {
        super.onResume()
        doctors = doctorDao.getAllDoctor()
        setUpView()
    }

    private fun setUpView() {
        doctorAdapter = DoctorAdapter(this, doctors)
        binding.doctorRecyclerView.adapter = doctorAdapter
        binding.doctorRecyclerView.layoutManager =
            LinearLayoutManager(this)

        doctorAdapter.setOnDoctorSelectedListener { doctor:Doctor, pos ->
            val data = Intent()
            data.putExtra("doctor", doctor)
            setResult(RESULT_OK, data)
            finish()
        }
    }
}