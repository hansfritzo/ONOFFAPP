package com.onoff.onoffapp.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onoff.onoffapp.BaseFragment
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.LoginFragmentBinding
import com.onoff.onoffapp.ui.viewmodel.LoginViewModel
import com.onoff.onoffapp.util.FlagConstants.TIME_LOADING
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {

            if (binding.editPassword.text.toString()
                    .isNotEmpty() && binding.editUsuario.text.toString().isNotEmpty()
            ) {

                binding.loading.visibility = View.VISIBLE
                binding.menuLogin.visibility = View.GONE
                binding.loading.startShimmer()
                binding.editUsuario.clearFocus()
                binding.editPassword.clearFocus()

                viewModel.getUserFromApi(binding.editUsuario.text.toString().trim(),
                    binding.editPassword.text.toString().trim())

            } else dialogBase(getString(R.string.usuarioContrasena))

        }

        viewModel.userModel.observe(this, { OK ->

            Handler(Looper.getMainLooper()).postDelayed({

                if (OK) findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
                else dialogBase(getString(R.string.validarUsuarioContrasena))
                loading()
            },
                TIME_LOADING
            )
        })

        viewModel.apiFailedModel.observe(this, { apiFailed ->

            dialogBase(apiFailed.message)
            loading()

        })
    }

    private fun loading() {
        if (binding.loading.isShimmerVisible) {
            binding.loading.stopShimmer()
            binding.loading.visibility = View.GONE
            binding.menuLogin.visibility = View.VISIBLE
        }
    }
}