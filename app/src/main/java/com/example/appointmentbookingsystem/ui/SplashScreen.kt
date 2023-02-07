package com.example.appointmentbookingsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appointmentbookingsystem.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnDoctor.setOnClickListener {
            val intent = Intent(this, RegisterDoctor::class.java)
            startActivity(intent)
        }

        binding.btnPatient.setOnClickListener {
            val intent = Intent(this, RegisterPatient::class.java)
            startActivity(intent)
        }

        binding.btnAppointment.setOnClickListener {
            startActivity(Intent(this, DashboardScreen::class.java))
        }
    }
}