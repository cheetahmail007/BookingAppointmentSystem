package com.example.appointmentbookingsystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appointmentbookingsystem.databinding.ComponentUpcomingScheduleCardBinding
import com.example.appointmentbookingsystem.dataclasses.UpcomingSchedules
import java.time.format.DateTimeFormatter

class DashboardUpcomingSchedulesAdapter(
    private val items: List<UpcomingSchedules>
): RecyclerView.Adapter<DashboardUpcomingSchedulesAdapter.VH>() {
    private lateinit var binding: ComponentUpcomingScheduleCardBinding

    inner class VH(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: UpcomingSchedules) {
            binding.doctorName.text = item.doctorData.name
            binding.appointmentSummary.text = "(${item.appointedTime.format(DateTimeFormatter.ISO_DATE)}) ${item.appointmentType}"
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