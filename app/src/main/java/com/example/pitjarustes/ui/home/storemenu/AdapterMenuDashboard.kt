package com.example.pitjarustes.ui.home.storemenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pitjarustes.databinding.ItemMenuBinding
import com.example.pitjarustes.model.MenuDashboard

class AdapterMenuDashboard(private val items: List<MenuDashboard>
) : RecyclerView.Adapter<AdapterMenuDashboard.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: MenuDashboard) {
            with(binding) {
                textMenu.text = menu.name
                imgMenu.setImageResource(menu.image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMenuBinding
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