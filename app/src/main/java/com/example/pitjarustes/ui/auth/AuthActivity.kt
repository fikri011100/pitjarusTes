package com.example.pitjarustes.ui.auth

import android.view.LayoutInflater
import com.example.pitjarustes.common.BaseActivity
import com.example.pitjarustes.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAuthBinding
        get() = ActivityAuthBinding::inflate

    override fun initView() {
        supportActionBar!!.hide()
    }
}