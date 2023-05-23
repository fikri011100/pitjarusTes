package com.example.pitjarustes.ui.home.detailstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.data.repository.store.StoreRepositoryImpl
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailStoreViewModel @Inject constructor(
    private val storeDao: StoreRepositoryImpl
) : ViewModel() {

    private val _store = MutableLiveData<Store>()
    val store: LiveData<Store>
        get() = _store
    var latlng: MutableLiveData<Pair<Double, Double>> = MutableLiveData()
    var latitude: MutableLiveData<Pair<Double, Double>> = MutableLiveData()
    var visit: MutableLiveData<Boolean> = MutableLiveData()

    fun getStoreById(id: Int) {
        viewModelScope.launch {
            _store.postValue(storeDao.getStoreById(id))
        }
    }

    fun setEnableToVisit() {
        visit.postValue(true)
    }

    fun getLatLng(long: Double, lat : Double) {
        latlng.postValue(Pair(lat, long))
    }

    fun getLatLong(lat: Double, long: Double) {
        latitude.postValue(Pair(lat, long))
    }
}