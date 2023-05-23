package com.example.pitjarustes.ui.home.storemenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pitjarustes.R
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.data.repository.store.StoreRepositoryImpl
import com.example.pitjarustes.model.Dashboard
import com.example.pitjarustes.model.Menu
import com.example.pitjarustes.model.MenuDashboard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreMenuViewModel @Inject constructor(
    private val storeDao: StoreRepositoryImpl
) : ViewModel() {

    private val _dashboard = MutableLiveData<List<Dashboard>>()
    val dashboard: LiveData<List<Dashboard>>
        get() = _dashboard

    private val _menu = MutableLiveData<List<MenuDashboard>>()
    val menu: LiveData<List<MenuDashboard>>
        get() = _menu

    private val _store = MutableLiveData<Store>()
    val store: LiveData<Store>
        get() = _store

    init {
        _dashboard.value = mutableListOf(
            Dashboard("OSA", R.drawable.bg_rounded_yellow, "September 2020", "50%", 40, 20),
            Dashboard("NPD", R.drawable.bg_rounded_green, "September 2020", "80%", 30, 28),
            Dashboard("APD", R.drawable.bg_rounded_blue, "September 2020", "90%", 40, 38),
            Dashboard("RSD", R.drawable.bg_rounded_yellow, "September 2020", "50%", 40, 20),
            Dashboard("INV", R.drawable.bg_rounded_green, "September 2020", "70%", 100, 70)
        )

        _menu.value = mutableListOf(
            MenuDashboard("Information", R.drawable.ic_information, 0),
            MenuDashboard("Product Check", R.drawable.ic_product_check, 0),
            MenuDashboard("SKU Promo", R.drawable.ic_cart, 1),
            MenuDashboard("Ringkasan OOS", R.drawable.ic_oos,1 ),
            MenuDashboard("Store Investment", R.drawable.ic_investment, 0)
        )
    }

    fun setVisited(id: Int) {
        viewModelScope.launch {
            storeDao.updateVisited(id, "1")
        }
    }

    fun getStoreById(id: Int) {
        viewModelScope.launch {
            _store.postValue(storeDao.getStoreById(id))
        }
    }
}