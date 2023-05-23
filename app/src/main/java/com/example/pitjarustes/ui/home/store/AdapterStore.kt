package com.example.pitjarustes.ui.home.store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.databinding.ItemStoreBinding
import com.example.pitjarustes.utils.DistanceFormatter
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil

class AdapterStore(
    private val items: List<Store>,
    private val latLng: LatLng,
    private val listener: (Store) -> Unit
) : RecyclerView.Adapter<AdapterStore.ViewHolder>() {

    var storeLatlg: LatLng? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStoreBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(
        private val binding: ItemStoreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {
            storeLatlg = LatLng(store.latitude!!, store.longitude!!)
            val distance = DistanceFormatter.getSpaceBetween(latLng, storeLatlg!!)
            with(binding) {
                textStore.text = store.name
                textCluster.text = "Cluster : ${store.cluster}"
                textType.text = "${store.display} - ${store.subdisplay}"
                textRadius.text = DistanceFormatter.format(distance)
                if (store.status.equals("1")) {
                    itemStatus.visibility = View.VISIBLE
                    itemStatus.text = "Perfect Store"
                }
            }
            itemView.setOnClickListener { listener(store) }
        }
    }
}