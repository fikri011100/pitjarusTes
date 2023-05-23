package com.example.pitjarustes.ui.home.store

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pitjarustes.R
import com.example.pitjarustes.common.BaseFragment
import com.example.pitjarustes.databinding.FragmentListStoreBinding
import com.example.pitjarustes.ui.home.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ListStoreFragment : BaseFragment<FragmentListStoreBinding>(), OnMapReadyCallback {

    private val viewModel: ListStoreViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListStoreBinding
        get() = FragmentListStoreBinding::inflate
    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var REQUEST_LOCATION_CODE = 101
    private var currentLocation: LatLng? = null
    lateinit var address: List<Address>
    private var latitude = 0.0
    private var longitude = 0.0

    override fun initObservable() {

        viewModel.getStore().observe(viewLifecycleOwner) { list ->
            viewModel.latitude.observe(viewLifecycleOwner) { longlat ->
                latitude = longlat.first
                longitude = longlat.second
                currentLocation = LatLng(latitude, longitude)
                binding.rvStore.adapter = AdapterStore(list, currentLocation!!) {
                    val bundle = bundleOf(
                        "id" to it.id,
                        "longitude" to longitude,
                        "latitude" to latitude
                    )
                    binding.root.findNavController()
                        .navigate(R.id.action_listStoreFragment_to_detailStoreFragment, bundle)
                }
            }
        }
    }

    override fun initView() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.rvStore.layoutManager = LinearLayoutManager(requireContext())
        if (!checkGPSEnabled()) {
            return
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                getLocation()
            } else {
                checkPermissions()
            }
        } else {
            getLocation()
        }
        binding.toolbar.imgBack.visibility = View.VISIBLE

        binding.toolbar.imgBack.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            activity?.finishAffinity()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    activity?.finishAffinity()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (isLocationEnabled()) {
            mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                val location: Location? = task.result
                if (location != null) {
                    val geocoder = Geocoder(requireContext(), Locale.getDefault())
                    address = geocoder.getFromLocation(location.latitude, location.longitude, 1)!!
                    viewModel.getLatLong(address[0].latitude, address[0].longitude)
                }
            }
        } else {
            Toast.makeText(requireActivity(), "Please turn on location", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    ACCESS_FINE_LOCATION
                )
            ) {
                AlertDialog.Builder(requireActivity())
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton("OK") { dialog, which ->
                        ActivityCompat.requestPermissions(
                            requireActivity(), arrayOf(
                                ACCESS_FINE_LOCATION
                            ), REQUEST_LOCATION_CODE
                        )
                    }
                    .create()
                    .show()

            } else ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_CODE
            )
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        viewModel.getStore().observe(viewLifecycleOwner) {
            val markerArr = arrayListOf<MarkerOptions>()
            for (a in it) {
                markerArr.add(MarkerOptions().position(LatLng(a.latitude!!, a.longitude!!)).title(a.name))
            }
            markerArr.forEach{
                mMap.addMarker(it)
            }
        }
        viewModel.latitude.observe(viewLifecycleOwner) {
            val markerIconPeople = bitmapDescriptorFromVector(requireContext(), R.drawable.ic_trace)
            val markerOptions = MarkerOptions()
                .position(LatLng(it.first, it.second))
                .title("Lokasi Anda")
                .icon(markerIconPeople)
            mMap.addMarker(markerOptions)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(it.first, it.second)))
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    private fun checkGPSEnabled(): Boolean {
        if (!isLocationEnabled())
            showAlert()
        return isLocationEnabled()
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Enable Location")
            .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " + "use this app")
            .setPositiveButton("Location Settings") { paramDialogInterface, paramInt ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }

}