package com.example.appointmentbookingsystem.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.PatientDao
import com.example.appointmentbookingsystem.database.entity.Patient
import com.example.appointmentbookingsystem.databinding.PatientItemBinding

class PatientAdapter(private var context: Context, private var patient: ArrayList<Patient>) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {
    private lateinit var binding: PatientItemBinding
    private lateinit var dbHelper: PatientDao


    override fun getItemCount(): Int {
        return patient.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = PatientItemBinding.inflate(layoutInflater, parent, false)
        return PatientViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.apply {
            val patientInfo = patient.get(position)
            txtPatientName.text = patientInfo.name
            txtPatientGender.text = patientInfo.gender
            txtPatientMobileNo.text = patientInfo.mobileNo

            btnUpdate.setOnClickListener {
                /*      val intent = Intent(context, EditPatient::class.java)
                      intent.putExtra(PATIENT_OBJECT, patient[position])
                      context.startActivity(intent)*/
            }

            btnDelete.setOnClickListener { view ->
                dbHelper = PatientDao(DbHelper(view.context))
                dbHelper.deletePatient(patientInfo.id)
                patient.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        if (this::patientSelectedListener.isInitialized) {
            holder.itemView.setOnClickListener {
                patientSelectedListener(patient[position], position)
            }
        }
    }

    inner class PatientViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val txtPatientName: TextView = binding.txtPatientName
        val txtPatientGender: TextView = binding.txtPatientGender
        val txtPatientMobileNo: TextView = binding.txtPatientMobileNo
        val btnDelete: AppCompatButton = binding.btnDelete
        val btnUpdate: AppCompatButton = binding.btnUpdate
    }

    fun setItems(newList: ArrayList<Patient>) {
        this.patient = newList
    }

    lateinit var patientSelectedListener: (Patient, Int) -> Unit
    fun setOnPatientSelectedListener(listener: (Patient, Int) -> Unit) {
        this.patientSelectedListener = listener
    }

    companion object {
        const val PATIENT_OBJECT = "patient object"
    }
}