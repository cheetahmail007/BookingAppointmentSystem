package com.example.appointmentbookingsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.appointmentbookingsystem.R
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.databinding.FragmentDoctorDetailBinding

class DoctorDetailFragment(
    private val doctorData: Doctor,
    private val patientId: Long
) : DialogFragment() {
    private lateinit var binding: FragmentDoctorDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDoctorDetailBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.doctorDetailName.text = doctorData.name
        binding.doctorDetailContact.text = "Email: ${doctorData.email} | Phone: ${doctorData.mobileNo}"

        binding.doctorDetailScheduleButton.setOnClickListener {
            Intent(context, BookAppointmentActivity::class.java).apply {
                putExtra(ARGS_DOCTOR_ID, doctorData.id)
                putExtra(ARGS_DOCTOR_NAME, doctorData.name)
                putExtra(ARGS_PATIENT_ID, patientId)
                startActivity(this)
            }
            dismiss()
        }
    }

    companion object {
        const val ARGS_DOCTOR_ID = "doctor_id"
        const val ARGS_DOCTOR_NAME = "doctor_name"
        const val ARGS_PATIENT_ID = "patient_id"
    }
}