package com.example.pitjarustes.ui.auth.login

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pitjarustes.R
import com.example.pitjarustes.common.BaseFragment
import com.example.pitjarustes.data.local.entity.Store
import com.example.pitjarustes.data.local.entity.User
import com.example.pitjarustes.databinding.FragmentLoginBinding
import com.example.pitjarustes.ui.home.MainActivity
import com.example.pitjarustes.utils.DataState
import com.example.pitjarustes.utils.isNotEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun initObservable() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it.state) {
                DataState.State.LOADING -> {

                }
                DataState.State.ERROR -> {
                    val dialog = AlertDialog.Builder(requireContext())
                    dialog.setTitle("Error API")
                        .setMessage("API is not avaliable.\nPlease use Dummy User to Continue this app")
                        .setPositiveButton("Create Dummy User") { paramDialogInterface, paramInt ->
                            viewModel.addUser(
                                User(
                                    1,
                                    "fikriim",
                                    "123qweasd",
                                    "Fikri Imadudin",
                                    "Mobile Dev",
                                    "23821932",
                                    R.drawable.imageprofile
                                )
                            )
                            viewModel.getStore().observe(viewLifecycleOwner) {
                                if (it.isEmpty()) {
                                    initData()
                                }
                            }
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            requireActivity().finish()
                        }
                        .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
                    dialog.show()
                }
                DataState.State.SUCCESS -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
                DataState.State.EMPTY -> {}
            }
        }
    }

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            if (initValidation()) viewModel.login(
                binding.edtUsername.toString().trim(),
                binding.edtPassword.toString().trim()
            )
        }
    }

    private fun initValidation(): Boolean {
        return (binding.edtUsername.isNotEmpty() && binding.edtPassword.isNotEmpty())
    }

    private fun initData() {
        viewModel.addStore(
            Store(
                1,
                "Toko A",
                "Small",
                "TT Reguler",
                "IRTM Big Store",
                "0",
                "JL. A Yani",
                true,
                true,
                true,
                R.drawable.tokoindomaret,
                "05-11-2018",
                112.66231006112723,
                -7.96502324655454
            )
        )
        viewModel.addStore(
            Store(
                2,
                "Toko B",
                "Small",
                "TT Reguler",
                "IRTM Big Store",
                "0",
                "JL. A Yani",
                true,
                true,
                true,
                R.drawable.tokoindomaret,
                "05-11-2018",
                112.66072657096291,
                -7.974188227177819
            )
        )
        viewModel.addStore(
            Store(
                3,
                "Toko C",
                "Small",
                "TT Reguler",
                "IRTM Big Store",
                "0",
                "JL. A Yani",
                true,
                true,
                true,
                R.drawable.tokoindomaret,
                "05-11-2018",
                112.66231006112723,
                -7.96502324655454
            )
        )
        viewModel.addStore(
            Store(
                4,
                "Toko B",
                "Small",
                "TT Reguler",
                "IRTM Big Store",
                "1",
                "JL. A Yani",
                true,
                false,
                true,
                R.drawable.tokoindomaret,
                "05-11-2018",
                112.66231006112723,
                -7.96502324655454
            )
        )
        viewModel.addStore(
            Store(
                5,
                "Toko A",
                "Small",
                "TT Reguler",
                "IRTM Big Store",
                "0",
                "JL. A Yani",
                true,
                false,
                true,
                R.drawable.tokoindomaret,
                "05-11-2018",
                112.66231006112723,
                -7.96502324655454
            )
        )
    }
}