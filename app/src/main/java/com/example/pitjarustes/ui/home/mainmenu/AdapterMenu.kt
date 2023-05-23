package com.example.pitjarustes.ui.home.mainmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pitjarustes.databinding.ItemMenuBinding
import com.example.pitjarustes.model.Menu

class AdapterMenu(
    private val items: List<Menu>,
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<AdapterMenu.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMenu.ViewHolder {
        return ViewHolder(
            ItemMenuBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AdapterMenu.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(
        private val binding: ItemMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu) {
            with(binding) {
                textMenu.text = menu.name
                imgMenu.setImageResource(menu.image)
            }
            itemView.setOnClickListener { listener(adapterPosition) }
        }

    }
}