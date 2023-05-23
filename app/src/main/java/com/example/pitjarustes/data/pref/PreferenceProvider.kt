package com.example.pitjarustes.data.pref

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceProvider @Inject constructor(
    private val preference: SharedPreferences
) {

    private val keyIsLogin = "IS_LOGIN"

    fun isLogin() = preference.getBoolean(keyIsLogin, false)

    fun setLogin() {
        val editor = preference.edit()
        editor.putBoolean(keyIsLogin, true)
        editor.apply()
    }

    fun logout(){
        val editor = preference.edit()
        editor.remove(keyIsLogin)
        editor.apply()
    }
}