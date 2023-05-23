package com.example.pitjarustes.ui.home.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.data.repository.store.StoreRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListStoreViewModel @Inject constructor(
    private val repository: StoreRepositoryImpl
) : ViewModel() {

    var userData: MutableLiveData<List<Store>> = MutableLiveData()
    var latitude: MutableLiveData<Pair<Double, Double>> = MutableLiveData()

    init {
        loadStore()
    }

    fun getStore(): MutableLiveData<List<Store>> {
        return userData
    }

    fun getLatLong(lat: Double, long: Double) {
        latitude.postValue(Pair(lat, long))
    }

    fun loadStore() {
        val list = repository.getAllStore()

        userData.postValue(list!!)
    }
}