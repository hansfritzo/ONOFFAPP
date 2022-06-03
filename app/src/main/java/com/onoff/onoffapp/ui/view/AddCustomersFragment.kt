package com.onoff.onoffapp.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.onoff.onoffapp.BaseFragment
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.AddCustomersFragmentBinding
import com.onoff.onoffapp.domain.model.CustomersRequest
import com.onoff.onoffapp.ui.viewmodel.AddCustomersViewModel
import com.onoff.onoffapp.util.FlagConstants
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
class AddCustomersFragment : BaseFragment() {

    companion object {
        fun newInstance() = AddCustomersFragment()
    }

    private lateinit var viewModel: AddCustomersViewModel
    private lateinit var binding: AddCustomersFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = AddCustomersFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCustomersViewModel::class.java)

        binding.btnEnviar.setOnClickListener {

            if (binding.edtPrimerNombre.text.toString().isNotEmpty()
                && binding.edtPrimerApellido.text.toString().isNotEmpty()
                && binding.edtTelefono.text.toString().isNotEmpty()
                && binding.edtDocumento.text.toString().isNotEmpty()
            ) {

                val customersRequest =
                    CustomersRequest(cDocument = binding.edtDocumento.text.toString()
                        .toLong(),
                        cName1 = binding.edtPrimerNombre.text.toString(),
                        cName2 = binding.edtSegundoNombre.text.toString(),
                        cLastName1 = binding.edtPrimerApellido.text.toString(),
                        cLastName2 = binding.edtSegundoApellido.text.toString(),
                        cpPhone = binding.edtTelefono.text.toString(),
                        isSync = true)

                val dialog = DialogConfirmacion()
                dialog.update("¿Estás seguro de crear el cliente:" + customersRequest.cName1.uppercase() + " " + customersRequest.cLastName1.uppercase() + " con documento: " + customersRequest.cDocument + "?")
                dialog.show(
                    requireActivity().supportFragmentManager.beginTransaction(),
                    DialogNotificaciones::class.java.name
                )

                dialog.clickedConfirmar.observe(this, Observer {

                    binding.loading.visibility = View.VISIBLE
                    binding.menuEnvio.visibility = View.GONE
                    binding.loading.startShimmer()
                    viewModel.setCustomers(customersRequest)

                })


            } else dialogBase(getString(R.string.campos))

        }

        viewModel.customersModel.observe(this, { OK ->

            Handler(Looper.getMainLooper()).postDelayed({

                binding.edtDocumento.text.clear()
                binding.edtPrimerNombre.text.clear()
                binding.edtSegundoNombre.text.clear()
                binding.edtPrimerApellido.text.clear()
                binding.edtSegundoApellido.text.clear()
                binding.edtTelefono.text.clear()

                dialogBase(getString(R.string.registrado))
                loading()
            },
                FlagConstants.TIME_LOADING
            )
        })

        viewModel.cunstoQueryModel.observe(this, { customersRequest ->

            Handler(Looper.getMainLooper()).postDelayed({

                dialogBase(mensaje = "Cliente con documento: " + customersRequest.cDocument + " ya existe en el sistema, por favor validar")

                loading()
            },
                FlagConstants.TIME_LOADING
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
            binding.menuEnvio.visibility = View.VISIBLE
        }
    }

}