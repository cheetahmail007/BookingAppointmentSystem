package com.example.appointmentbookingsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appointmentbookingsystem.database.entity.AppointmentDetails
import com.example.appointmentbookingsystem.databinding.ComponentUpcomingScheduleCardBinding
import com.example.appointmentbookingsystem.dataclasses.UpcomingSchedules
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class DashboardUpcomingSchedulesAdapter(
    private val items: List<AppointmentDetails>
): RecyclerView.Adapter<DashboardUpcomingSchedulesAdapter.VH>() {
    private lateinit var binding: ComponentUpcomingScheduleCardBinding

    inner class VH(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: AppointmentDetails) {
            binding.doctorName.text = item.doctorName
            binding.appointmentSummary.text = LocalDateTime
                .ofEpochSecond(item.aptDateTime, 0, ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_DATE)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        ComponentUpcomingScheduleCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            binding = this
        }.run{ VH(root) }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
}