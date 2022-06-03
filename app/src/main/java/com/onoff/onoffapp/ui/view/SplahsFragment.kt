package com.onoff.onoffapp.ui.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.onoff.onoffapp.BaseFragment
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.SplahsFragmentBinding
import com.onoff.onoffapp.ui.viewmodel.SplahsViewModel
import com.onoff.onoffapp.util.FlagConstants.TIME_LOADING
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
class SplahsFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplahsFragment()
    }

    private lateinit var viewModel: SplahsViewModel
    private lateinit var binding: SplahsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = SplahsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SplahsViewModel::class.java]

        versionControl()
        binding.loading.startShimmer()
        binding.txtCopyright.text = versionControl()
        viewModel.getUserDataBase()
        viewModel.userModel.observe(this, Observer { OK ->

            Handler(Looper.getMainLooper()).postDelayed({

                binding.imgLogoEnd.visibility = View.VISIBLE
                if (binding.loading.isShimmerVisible) {
                    binding.loading.stopShimmer()
                    binding.loading.visibility = View.GONE
                }

                if (OK) findNavController().navigate(R.id.action_splahsFragment_to_homeActivity)
                else findNavController().navigate(R.id.action_splahsFragment_to_loginFragment)

            }, TIME_LOADING)

        })


    }

    @SuppressLint("SetTextI18n")
    fun versionControl(): String {
        val powered = getString(R.string.version)
        return try {
            val versionName = " " + requireActivity().packageManager
                .getPackageInfo(requireActivity().packageName, 0).versionName
            powered + versionName

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            powered
        }

    }

}
