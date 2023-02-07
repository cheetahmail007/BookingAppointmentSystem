package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appointmentbookingsystem.R

// Assigning it to Alex
//Make UI to get register patient and call add patient -> create UI add method in Dao
class RegisterPatient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_patient)
    }
}