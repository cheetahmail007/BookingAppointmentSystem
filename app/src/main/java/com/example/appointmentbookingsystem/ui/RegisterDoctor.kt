package com.example.appointmentbookingsystem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.DoctorDao
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.databinding.ActivityRegisterDoctorBinding

fun String.convertedInt(): Int {
    if (this == "") return 0
    var res = 0
    try {
        res = this.toInt()
    } catch (e: java.lang.Exception) {
    }
    return res
}

class RegisterDoctor : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterDoctorBinding
    private lateinit var dbHelper: DbHelper
    private lateinit var doctorDao: DoctorDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()
        initViews()
    }

    private fun initDatabase() {
        dbHelper = DbHelper(this.applicationContext)
        doctorDao = DoctorDao(dbHelper)
    }

    private fun initViews() {
        binding.btnAdd.setOnClickListener {
            val name = binding.editName.text.toString()
            val mobileNo = binding.editMobileNo.text.toString()
            val gender = binding.editGender.text.toString()
            val specialization = binding.editSpecialization.text.toString()
            val location = binding.editLocation.text.toString()
            val rating = binding.editRating.text.toString()
            val experience = binding.editExperience.text.toString()
            val about = binding.editAbout.text.toString()
            val email = binding.editEmail.text.toString()

            if (doctorDao.addDoctor(
                    Doctor(
                        0,
                        name,
                        mobileNo,
                        gender,
                        specialization,
                        location,
                        rating.convertedInt(),
                        experience.convertedInt(),
                        about,
                        email
                    )
                ) > 0
            ) {
                finish()
            }
        }
    }
}