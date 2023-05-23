package com.example.pitjarustes.ui.home.mainmenu

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pitjarustes.common.BaseFragment
import com.example.pitjarustes.databinding.FragmentMainBinding
import com.example.pitjarustes.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun initObservable() {
        viewModel.menus.observe(viewLifecycleOwner) { list ->
            binding.rvMenu.adapter = AdapterMenu(list) {
                when (it) {
                    0 -> {
                        val action = MainFragmentDirections.actionMainFragmentToListStoreFragment()
                        binding.root.findNavController().navigate(action)
                    }
                    1 -> {}
                    2 -> {}
                    3 -> {}
                    4 -> {
                        viewModel.logoutUser()
                        val intent = Intent(requireContext(), AuthActivity::class.java)
                        startActivity(intent)
                        requireActivity().finishAffinity()
                    }
                }
            }
        }
        viewModel.user.observe(viewLifecycleOwner) {
            binding.imageProfile.setImageResource(it.photo!!)
            binding.textName.text = it.fullname
            binding.textRole.text = "Role : { ${it.role} }"
            binding.textNik.text = "NIK : ${it.nik}"
        }
    }

    override fun initView() {
        binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 4)
        viewModel.getUser()
    }

}