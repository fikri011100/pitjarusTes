package com.example.pitjarustes.ui.home.detailstore

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.pitjarustes.common.BaseFragment
import com.example.pitjarustes.databinding.FragmentDetailStoreBinding
import com.example.pitjarustes.utils.DistanceFormatter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailStoreFragment : BaseFragment<FragmentDetailStoreBinding>() {

    private val viewModel: DetailStoreViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailStoreBinding
        get() = FragmentDetailStoreBinding::inflate
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var address: List<Address>
    private var longitude = 0.0
    private var latitude = 0.0
    private var idStore = 0

    override fun initObservable() {
        viewModel.store.observe(viewLifecycleOwner) {
            viewModel.getLatLng(it.longitude!!, it.latitude!!)
            binding.textStore.text = it.name
            binding.textAlamat.text = it.alamat
            binding.textOutlet.text = it.cluster
            binding.textDisplay.text = it.display
            binding.textSubDisplay.text = it.subdisplay
            binding.imgStore.setImageResource(it.image!!)
            if (it.ertm == true) binding.textErtm.text = "Ya" else binding.textErtm.text = "Tidak"
            if (it.pareto == true) binding.textPareto.text = "Ya" else binding.textPareto.text =
                "Tidak"
            if (it.merchandise == true) binding.textMerchandise.text =
                "Ya" else binding.textMerchandise.text = "Tidak"

            viewModel.latitude.observe(viewLifecycleOwner) { pair ->
                val newLoc = LatLng(pair.first, pair.second)
                val currentLoc = LatLng(it.latitude, it.longitude)
                val distance = DistanceFormatter.getSpaceBetween(newLoc, currentLoc)

                if (distance > 100) {
                    Toast.makeText(requireActivity(), "Lokasi Belum Sesuai", Toast.LENGTH_SHORT)
                        .show()
                    binding.textLocationStatus.text = "Lokasi Belum Sesuai"
                    binding.btnReset.visibility = View.VISIBLE
                    binding.btnReset.setOnClickListener {
                        getLocation()
                    }
                } else {
                    binding.btnReset.visibility = View.GONE
                    binding.textLocationStatus.text = "Lokasi Sudah Sesuai"
                    viewModel.setEnableToVisit()
                }
            }
        }
        viewModel.latlng.observe(viewLifecycleOwner) {
            val distance = DistanceFormatter.getSpaceBetween(
                LatLng(it.first, it.second),
                LatLng(latitude, longitude)
            )
            if (distance > 100) {
                binding.textLocationStatus.text = "Lokasi Belum Sesuai"
                binding.btnReset.visibility = View.VISIBLE
                binding.btnReset.setOnClickListener {
                    getLocation()
                }
            } else {
                viewModel.setEnableToVisit()
            }
        }
        viewModel.visit.observe(viewLifecycleOwner) { store ->
            binding.btnVisit.setOnClickListener {
                val directions =
                    DetailStoreFragmentDirections.actionDetailStoreFragmentToStoreMenuFragment(
                        idStore
                    )
                binding.root.findNavController()
                    .navigate(directions)
            }
        }
    }

    override fun initView() {
        idStore = arguments?.getInt("id")!!
        longitude = arguments?.getDouble("longitude")!!
        latitude = arguments?.getDouble("latitude")!!
        viewModel.getStoreById(idStore)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
            val location: Location? = task.result
            if (location != null) {
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                address = geocoder.getFromLocation(location.latitude, location.longitude, 1)!!
                viewModel.getLatLong(address[0].latitude, address[0].longitude)
            }
        }
    }

}