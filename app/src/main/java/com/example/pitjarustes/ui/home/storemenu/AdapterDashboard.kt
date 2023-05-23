package com.example.pitjarustes.ui.home.storemenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pitjarustes.databinding.ItemDashboardBinding
import com.example.pitjarustes.model.Dashboard

class AdapterDashboard (private val items: List<Dashboard>
) : RecyclerView.Adapter<AdapterDashboard.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemDashboardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dashboard: Dashboard) {
            with(binding) {
                textTitle.text = dashboard.title
                textDate.text = dashboard.month
                textPercentage.text = dashboard.value
                textTarget.text = dashboard.target.toString()
                textAchievement.text = dashboard.achievement.toString()
                bgDashboard.setBackgroundResource(dashboard.image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDashboardBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}