package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.appointmentbookingsystem.R
import com.example.appointmentbookingsystem.adapters.DashboardNearbyDoctorsAdapter
import com.example.appointmentbookingsystem.adapters.DashboardUpcomingSchedulesAdapter
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.AppointmentDao
import com.example.appointmentbookingsystem.database.dao.DoctorDao
import com.example.appointmentbookingsystem.database.dao.PatientDao
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.database.entity.Patient
import com.example.appointmentbookingsystem.databinding.ActivityDashboardScreenBinding

class DashboardScreen : AppCompatActivity() {
    private val binding by lazy { ActivityDashboardScreenBinding.inflate(layoutInflater) }
    private lateinit var patient: Patient

    private lateinit var patientDao: PatientDao
    private lateinit var doctorDao: DoctorDao
    private lateinit var appointmentDao: AppointmentDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        patientDao = PatientDao(DbHelper(applicationContext))
        doctorDao = DoctorDao(DbHelper(applicationContext))
        appointmentDao = AppointmentDao(applicationContext)

        patient = patientDao.getAllPatient().takeIf { it.size > 0 }?.last() ?: Patient(
            0L,
            "Anonymous",
            "anonymous@gmail.com",
            "(000) 000-0000",
            "male",
            "",
            "Ligma",
        )

        binding.dashboardUserNameDisplay.text = patient.name

        binding.dashboardUpcomingSchedule.apply {
            layoutManager = LinearLayoutManager(this@DashboardScreen, LinearLayoutManager.HORIZONTAL, false)
            adapter = DashboardUpcomingSchedulesAdapter(appointmentDao.getAllBookedAppointments())
            PagerSnapHelper().attachToRecyclerView(this)
            displayNoItems( this, binding.dashboardNoAppointmentsMessage )
        }


        binding.dashboardAvailableDoctors.apply {
            layoutManager = LinearLayoutManager(this@DashboardScreen)
            adapter = DashboardNearbyDoctorsAdapter(doctorDao.getAllDoctor(), ::openDoctorDetail)
            displayNoItems( this, binding.dashboardNoDoctorMessage )
        }
    }

    private fun displayNoItems(
        recycleViewIfPopulated: RecyclerView,
        viewIfEmpty: View
    ) {
        if ((recycleViewIfPopulated.adapter?.itemCount ?: 0) > 0) {
            recycleViewIfPopulated.visibility = View.VISIBLE
            viewIfEmpty.visibility = View.INVISIBLE
        } else {
            recycleViewIfPopulated.visibility = View.INVISIBLE
            viewIfEmpty.visibility = View.VISIBLE
        }
    }

    private fun openDoctorDetail(doctorData: Doctor) {
        DoctorDetailFragment(doctorData, patient.id).apply {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog)
        }.show(supportFragmentManager, "Doctor Detail Fragment")
    }
}