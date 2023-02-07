package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appointmentbookingsystem.databinding.ActivityScheduleAppointmentBinding

class ScheduleAppointment : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleAppointmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}