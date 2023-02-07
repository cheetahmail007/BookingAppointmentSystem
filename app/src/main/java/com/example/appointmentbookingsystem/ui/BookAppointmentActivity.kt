package com.example.appointmentbookingsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.appointmentbookingsystem.database.dao.AppointmentDao
import com.example.appointmentbookingsystem.database.entity.Appointment
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.database.entity.Patient
import com.example.appointmentbookingsystem.databinding.ActivityBookAppointmentBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookAppointmentBinding
    private lateinit var appointmentDao: AppointmentDao
    lateinit var selectedPatient: Patient
    lateinit var selectedDoctor: Doctor
    var selectedYear = -1
    var selectedMonth = -1
    var selectedDay = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        doDbWork()
    }

    private fun doDbWork() {
        appointmentDao = AppointmentDao(this)
        binding.apply {
            btnSelectPatient.setOnClickListener {
                val intent = Intent(baseContext, ShowPatient::class.java)
                startActivityForResult(intent, RC_PATIENT)
            }

            btnSelectDoctor.setOnClickListener {
                val intent = Intent(baseContext, ShowDoctorActivity::class.java)
                startActivityForResult(intent, RC_DOCTOR)
            }
            btnSBookAppointment.setOnClickListener {
                bookAppointment()
            }

            calenderDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
                selectedYear = year
                selectedMonth = month
                selectedDay = dayOfMonth
            }
        }
    }

    private fun bookAppointment() {
        if (!this::selectedDoctor.isInitialized) {
            Snackbar.make(binding.mainLayout, "Please select doctor", Snackbar.LENGTH_LONG).show()
            return
        }

        if (!this::selectedPatient.isInitialized) {
            Snackbar.make(binding.mainLayout, "Please select patient", Snackbar.LENGTH_LONG).show()
            return
        }

        val hour = binding.timepicker.hour
        val minute = binding.timepicker.minute

        val calender = Calendar.getInstance()
        calender.set(Calendar.YEAR, selectedYear)
        calender.set(Calendar.MONTH, selectedMonth)
        calender.set(Calendar.DAY_OF_MONTH, selectedDay)

        calender.set(Calendar.HOUR, hour)
        calender.set(Calendar.MINUTE, minute)

        val aptDateTime = calender.time.time

        val appointment =
            Appointment(0, selectedDoctor.id, selectedPatient.id, aptDateTime)
        val apNo = appointmentDao.bookAppointment(appointment)

        if (apNo > 0) {
            showMessage("Success!", "Appointment booked successfully")
        } else {
            showMessage("Failed", "Failed to book appointment")
        }
    }

    private fun showMessage(result: String, message: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(result)
            .setMessage(message)
            .setPositiveButton("Ok") { d, _ ->
                d.dismiss()
            }
            .create()
        dialog.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == RC_DOCTOR) {
                val doctor = data?.extras?.getParcelable<Doctor>("doctor")
                doctor?.let { selectedDoctor = it }
                binding.txtSelectedDoctor.text = doctor?.name
            } else if (requestCode == RC_PATIENT) {
                val patient = data?.extras?.getParcelable<Patient>("patient")
                patient?.let { selectedPatient = it }
                binding.txtSelectedPatient.text = patient?.name
            }
        }
    }

    companion object {
        const val RC_PATIENT = 10
        const val RC_DOCTOR = 20
    }
}