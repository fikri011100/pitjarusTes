package com.example.pitjarustes.ui.home.mainmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pitjarustes.R
import com.example.pitjarustes.data.local.UserDao
import com.example.pitjarustes.data.local.entity.User
import com.example.pitjarustes.data.pref.PreferenceProvider
import com.example.pitjarustes.model.Menu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDao: UserDao,
    private val pref: PreferenceProvider
) : ViewModel() {

    private val _menus = MutableLiveData<List<Menu>>()
    val menus: LiveData<List<Menu>>
        get() = _menus

    var user = MutableLiveData<User>()

    init {
        _menus.value = mutableListOf(
            Menu("Kunjungan", R.drawable.ic_kunjungan),
            Menu("Target Install CDFDPC", R.drawable.ic_target),
            Menu("Dashboard", R.drawable.ic_dashboard),
            Menu("Transmission History", R.drawable.ic_history),
            Menu("Logout", R.drawable.ic_logout)
        )
    }

    fun logoutUser() {
        viewModelScope.launch {
            userDao.clearUser()
            pref.logout()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            user.postValue(userDao.getUserById())
        }
    }
}