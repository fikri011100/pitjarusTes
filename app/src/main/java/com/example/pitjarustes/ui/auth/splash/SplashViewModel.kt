package com.example.pitjarustes.ui.auth.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pitjarustes.data.pref.PreferenceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pref : PreferenceProvider
) : ViewModel() {

    fun check() = viewModelScope.launch {
        delay(5000)
    }

    fun isLogin() : Boolean {
        return pref.isLogin()
    }
}