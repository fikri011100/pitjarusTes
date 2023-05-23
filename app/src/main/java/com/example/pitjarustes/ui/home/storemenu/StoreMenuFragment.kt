package com.example.pitjarustes.ui.home.storemenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pitjarustes.R
import com.example.pitjarustes.common.BaseFragment
import com.example.pitjarustes.databinding.FragmentStoreMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreMenuFragment : BaseFragment<FragmentStoreMenuBinding>() {

    private val viewModel : StoreMenuViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStoreMenuBinding
        get() = FragmentStoreMenuBinding::inflate
    private var idStore = 0

    override fun initObservable() {
        viewModel.menu.observe(viewLifecycleOwner) {
            binding.rvMenu.adapter = AdapterMenuDashboard(it)
        }
        viewModel.dashboard.observe(viewLifecycleOwner) {
            binding.rvStore.adapter = AdapterDashboard(it)
        }
        binding.btnVisit.setOnClickListener {
            viewModel.setVisited(idStore)
            val navigate = StoreMenuFragmentDirections.actionStoreMenuFragmentToListStoreFragment()
            findNavController().navigate(navigate)
        }
        viewModel.store.observe(viewLifecycleOwner) {
            binding.textId.text = "ST000${it.id}"
            binding.textStoreName.text = it.name
            binding.textStoreCluster.text = "Cluster : ${it.cluster}"
            if (it.pareto!!) binding.textStoreType.text = "${it.display} - ${it.subdisplay} - Pareto"
            else binding.textStoreType.text = "${it.display} - ${it.subdisplay}"
            binding.imageToko.setImageResource(it.image!!)
        }
    }

    override fun initView() {
        idStore = arguments?.getInt("arg_id")!!
        binding.textMarqueeInfo.isSelected = true
        binding.rvMenu.isNestedScrollingEnabled = false
        binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvStore.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.getStoreById(idStore)
    }

}