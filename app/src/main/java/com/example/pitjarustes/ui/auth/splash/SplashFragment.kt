package com.example.pitjarustes.ui.auth.splash

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pitjarustes.common.BaseFragment
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.databinding.FragmentSplashBinding
import com.example.pitjarustes.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val viewModel: SplashViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun initObservable() {
        if (viewModel.isLogin()) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        } else {
            val direction = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            findNavController().navigate(direction)
        }
    }

    override fun initView() {
        viewModel.check()

    }

}