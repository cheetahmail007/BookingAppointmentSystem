package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.appointmentbookingsystem.R
import com.example.appointmentbookingsystem.adapters.DashboardNearbyDoctorsAdapter
import com.example.appointmentbookingsystem.adapters.DashboardUpcomingSchedulesAdapter
import com.example.appointmentbookingsystem.database.Constant
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.database.entity.Patient
import com.example.appointmentbookingsystem.databinding.ActivityDashboardScreenBinding
import com.example.appointmentbookingsystem.dataclasses.UpcomingSchedules
import java.time.LocalDateTime

class DashboardScreen : AppCompatActivity() {
    private val binding by lazy { ActivityDashboardScreenBinding.inflate(layoutInflater) }
    private lateinit var patient: Patient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        patient = Patient(
            2L,
            "Catch",
            "cath@gmail.com",
            "(000) 000-0000",
            "male",
            "556 Ave",
            "nothing",
        )

        binding.dashboardUpcomingSchedule.apply {
            layoutManager = LinearLayoutManager(this@DashboardScreen, LinearLayoutManager.HORIZONTAL, false)
            adapter = DashboardUpcomingSchedulesAdapter(placeholderSchedule)
            PagerSnapHelper().attachToRecyclerView(this)
        }

        binding.dashboardAvailableDoctors.apply {
            layoutManager = LinearLayoutManager(this@DashboardScreen)
            adapter = DashboardNearbyDoctorsAdapter(placeholderDoctors, ::openDoctorDetail)
        }
    }

    private fun openDoctorDetail(doctorData: Doctor) {
        DoctorDetailFragment(doctorData, patient.id).apply {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog)
        }.show(supportFragmentManager, "Doctor Detail Fragment")
    }

    companion object {
        val placeholderDoctors = listOf(
            Doctor(
                0L,
                "Joe",
                "(000) 000-0000",
                "male",
                "",
                "",
                0,
                0,
                "",
                ""
            ),
            Doctor(
                1L,
                "Dunwanna",
                "(000) 000-0000",
                "male",
                "",
                "",
                0,
                0,
                "",
                ""
            ),
            Doctor(
                2L,
                "Lisa",
                "(000) 000-0000",
                "not male",
                "",
                "",
                0,
                0,
                "",
                ""
            ),
            Doctor(
                3L,
                "Patient",
                "(000) 000-0000",
                "male",
                "",
                "",
                0,
                0,
                "",
                ""
            ),
            Doctor(
                4L,
                "Not Doctor",
                "(000) 000-0000",
                "undefined",
                "",
                "",
                0,
                0,
                "",
                ""
            )
        )

        val placeholderSchedule = listOf(
            UpcomingSchedules(
                placeholderDoctors[0],
                Patient(
                    0L,
                    "Smith",
                    "smith@gmail.com",
                    "(000) 000-0000",
                    "male",
                    "123 Street",
                    "Ill"
                ),
                Constant.AppointmentType.AUTOPSY,
                LocalDateTime.now(),
                ""
            ),
            UpcomingSchedules(
                placeholderDoctors[1],
                Patient(
                    2L,
                    "Catch",
                    "cath@gmail.com",
                    "(000) 000-0000",
                    "male",
                    "556 Ave",
                    "nothing",
                ),
                Constant.AppointmentType.LIGMA,
                LocalDateTime.now(),
                ""
            )
        )
    }
}