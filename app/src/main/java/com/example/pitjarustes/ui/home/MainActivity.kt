package com.example.pitjarustes.ui.home

import android.view.LayoutInflater
import com.example.pitjarustes.common.BaseActivity
import com.example.pitjarustes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initView() {
        supportActionBar!!.hide()
    }
}