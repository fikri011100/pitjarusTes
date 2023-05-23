package com.example.pitjarustes.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.data.local.entity.User
import com.example.pitjarustes.data.network.response.AuthResponse
import com.example.pitjarustes.data.pref.PreferenceProvider
import com.example.pitjarustes.data.repository.auth.AuthRepository
import com.example.pitjarustes.data.repository.store.StoreRepositoryImpl
import com.example.pitjarustes.data.repository.user.UserRepositoryImpl
import com.example.pitjarustes.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val locale: UserRepositoryImpl,
    private val storeDao: StoreRepositoryImpl,
    private val pref: PreferenceProvider
) : ViewModel() {

    private val _viewState = MutableLiveData<DataState<AuthResponse>>()
    val viewState: LiveData<DataState<AuthResponse>>
        get() = _viewState
    var userData: MutableLiveData<List<Store>> = MutableLiveData()

    init {
        loadStore()
    }

    fun getStore(): MutableLiveData<List<Store>> {
        return userData
    }

    fun login(username: String, password: String) = viewModelScope.launch {
        _viewState.postValue(DataState.loading(null))
        delay(1000)
        try {
            val response = repo.toLogin(username, password)!!
            _viewState.postValue(DataState.success(response))

        } catch (e: Exception) {
            _viewState.postValue(DataState.error(e.toString()))
        }
    }

    fun addStore(store: Store) {
        viewModelScope.launch {
            storeDao.insertStore(store)
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            locale.addUser(user)
            pref.setLogin()
        }
    }

    fun loadStore() {
        val list = storeDao.getAllStore()

        userData.postValue(list!!)
    }
}