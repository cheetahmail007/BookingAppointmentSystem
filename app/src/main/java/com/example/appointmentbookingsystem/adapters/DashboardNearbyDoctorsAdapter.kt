package com.example.appointmentbookingsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appointmentbookingsystem.database.entity.Doctor
import com.example.appointmentbookingsystem.databinding.ComponentNearbyDoctorsCardBinding

class DashboardNearbyDoctorsAdapter(
    private val items: List<Doctor>
): RecyclerView.Adapter<DashboardNearbyDoctorsAdapter.VH>() {
    private lateinit var binding: ComponentNearbyDoctorsCardBinding

    inner class VH(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: Doctor) {
            binding.doctorName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        ComponentNearbyDoctorsCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            binding = this
        }.run{ VH(root) }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
}