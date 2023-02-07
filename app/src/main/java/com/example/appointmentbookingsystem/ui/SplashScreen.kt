package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appointmentbookingsystem.R


// Luan - add two buttons -
//button1- call activity - Register doctor screen
//button2- call activity - Register patient screen

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }
}