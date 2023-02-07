package com.example.appointmentbookingsystem.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.appointmentbookingsystem.database.DbHelper
import com.example.appointmentbookingsystem.database.dao.DoctorDao
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.databinding.DoctorItemBinding

class DoctorAdapter(private var context: Context, private var doctor: ArrayList<Doctor>) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private lateinit var binding: DoctorItemBinding
    private lateinit var dbHelper: DoctorDao


    override fun getItemCount(): Int {
        return doctor.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DoctorItemBinding.inflate(layoutInflater, parent, false)
        return DoctorViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.apply {
            val doctorInfo = doctor[position]
            txtDoctorName.text = doctorInfo.name
            txtDoctorMobileNo.text = doctorInfo.mobileNo

            /*btnUpdate.setOnClickListener {
                val intent = Intent(context, EditPatient::class.java)
                intent.putExtra(DOCTOR_OBJECT, doctor[position])
                context.startActivity(intent)
            }*/

            btnDelete.setOnClickListener { view ->
                dbHelper = DoctorDao(DbHelper(view.context))
                dbHelper.deleteDoctor(doctorInfo.id)
                doctor.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        if (this::doctorSelectedListener.isInitialized) {
            holder.itemView.setOnClickListener {
                doctorSelectedListener(doctor[position], position)
            }
        }
    }

    fun setItems(newList: ArrayList<Doctor>) {
        this.doctor = newList
    }

    lateinit var doctorSelectedListener: (Doctor, Int) -> Unit
    fun setOnDoctorSelectedListener(listener: (Doctor, Int) -> Unit) {
        this.doctorSelectedListener = listener
    }

    inner class DoctorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val txtDoctorName: TextView = binding.txtDoctorName
        val txtDoctorMobileNo: TextView = binding.txtDoctorMobileNo
        val btnDelete: AppCompatButton = binding.btnDelete
        val btnUpdate: AppCompatButton = binding.btnUpdate
    }

    companion object {
        const val DOCTOR_OBJECT = "doctor object"
    }
}

