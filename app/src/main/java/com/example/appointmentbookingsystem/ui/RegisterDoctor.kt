package com.example.appointmentbookingsystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.appointmentbookingsystem.R
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.DoctorDao
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.databinding.ActivityRegisterDoctorBinding

fun String.convertedInt(): Int {
    if(this == "") return 0
    var res = 0
    try {
        res = this.toInt()
    } catch (e: java.lang.Exception) {
    }
    return res
}
//Add doctor in screen - Thomas
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
        binding.spinnerGender.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, GENDER_LIST)
        binding.spinnerSpecialization.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, DOCTOR_SPECS)
        binding.btnAdd.setOnClickListener {
            registerDoctor()
        }
    }

    fun registerDoctor() {
        val name = binding.editName.text.toString()
        val mobileNo = binding.editMobileNo.text.toString()
        val gender = binding.spinnerGender.selectedItem.toString()
        val specialization = binding.spinnerSpecialization.selectedItem.toString()
        val location = binding.editLocation.text.toString()
        val rating = binding.editRating.text.toString()
        val experience = binding.editExperience.text.toString()
        val about = binding.editAbout.text.toString()
        val email = binding.editEmail.text.toString()
        if(doctorDao.addDoctor(
            Doctor(
                69L,
                name,
                mobileNo,
                gender,
                specialization,
                location,
                rating.convertedInt(),
                experience.convertedInt(),
                about,
                email)
        ) > 0) {
            finish()
        }

        Toast.makeText(this, "Doctor $name added!", Toast.LENGTH_SHORT).show()
        clearForm()
    }

    fun clearForm() {
        binding.editName.setText("")
        binding.editMobileNo.setText("")
        binding.spinnerGender.setSelection(0)
        binding.spinnerSpecialization.setSelection(0)
        binding.editRating.setText("")
        binding.editExperience.setText("")
        binding.editAbout.setText("")
        binding.editEmail.setText("")
        binding.editLocation.setText("")
    }

    companion object {
        val GENDER_LIST = arrayOf("Male", "Female")
        val DOCTOR_SPECS = arrayOf("Specialization1", "Specialization2", "Specialization3", "Specialization4", "Specialization5")

    }

}