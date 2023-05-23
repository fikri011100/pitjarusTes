package com.example.pitjarustes.utils

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import java.text.DecimalFormat

class DistanceFormatter {
    companion object {
        fun format(distance: Double): String {
            val df = DecimalFormat("#.##")
            return when {
                distance < 1000 -> "${df.format(distance)} m"
                else -> "${df.format(distance / 1000)} km"
            }
        }

        fun getSpaceBetween(point1: LatLng, point2: LatLng) : Double {
            return SphericalUtil.computeDistanceBetween(point1, point2)
        }
    }
}