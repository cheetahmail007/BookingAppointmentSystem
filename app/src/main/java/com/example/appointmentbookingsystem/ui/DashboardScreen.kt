package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.dashboardUpcomingSchedule.apply {
            layoutManager = LinearLayoutManager(this@DashboardScreen, LinearLayoutManager.HORIZONTAL, false)
            adapter = DashboardUpcomingSchedulesAdapter(placeholderSchedule)
            PagerSnapHelper().attachToRecyclerView(this)
        }

        binding.dashboardAvailableDoctors.apply {
            layoutManager = LinearLayoutManager(this@DashboardScreen)
            adapter = DashboardNearbyDoctorsAdapter(placeholderDoctors)
        }
    }

    companion object {
        val placeholderDoctors = listOf(
            Doctor(
                "0",
                "Joe",
                "(000) 000-0000",
                "male"
            ),
            Doctor(
                "1",
                "Dunwanna",
                "(000) 000-0000",
                "male"
            ),
            Doctor(
                "2",
                "Lisa",
                "(000) 000-0000",
                "not male"
            ),
            Doctor(
                "3",
                "Patient",
                "(000) 000-0000",
                "male"
            ),
            Doctor(
                "4",
                "Not Doctor",
                "(000) 000-0000",
                "undefined"
            )
        )

        val placeholderSchedule = listOf(
            UpcomingSchedules(
                placeholderDoctors[0],
                Patient(
                    "0",
                    "Smith",
                    "(000) 000-0000",
                    "male"
                ),
                Constant.AppointmentType.AUTOPSY,
                LocalDateTime.now(),
                ""
            ),
            UpcomingSchedules(
                placeholderDoctors[1],
                Patient(
                    "2",
                    "Catch",
                    "(000) 000-0000",
                    "male"
                ),
                Constant.AppointmentType.LIGMA,
                LocalDateTime.now(),
                ""
            )
        )
    }
}