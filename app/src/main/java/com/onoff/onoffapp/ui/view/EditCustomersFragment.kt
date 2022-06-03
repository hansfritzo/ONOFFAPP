package com.onoff.onoffapp.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.onoff.onoffapp.BaseFragment
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.EditCustomersFragmentBinding
import com.onoff.onoffapp.domain.model.Customers
import com.onoff.onoffapp.domain.model.CustomersUpdateRequest
import com.onoff.onoffapp.ui.viewmodel.EditCustomersViewModel
import com.onoff.onoffapp.util.FlagConstants.TIME_LOADING
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
class EditCustomersFragment : BaseFragment() {

    companion object {
        fun newInstance() = EditCustomersFragment()
    }

    private lateinit var viewModel: EditCustomersViewModel
    private lateinit var binding: EditCustomersFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = EditCustomersFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditCustomersViewModel::class.java)

        val customers = arguments!!.getSerializable("customers") as Customers

        binding.edtDocumento.setText(customers.cDocument.toString())
        binding.edtPrimerNombre.setText(customers.cName1)
        binding.edtSegundoNombre.setText(customers.cName2)
        binding.edtPrimerApellido.setText(customers.cLastName1)
        binding.edtSegundoApellido.setText(customers.cLastName2)

        binding.btnEnviar.setOnClickListener {

            if (binding.edtPrimerNombre.text.toString().isNotEmpty()
                && binding.edtPrimerApellido.text.toString().isNotEmpty()
                && binding.edtDocumento.text.toString().isNotEmpty()
            ) {

                val customersUpdateRequest =
                    CustomersUpdateRequest(cDocument = binding.edtDocumento.text.toString()
                        .toLong(),
                        cName1 = binding.edtPrimerNombre.text.toString(),
                        cName2 = binding.edtSegundoNombre.text.toString(),
                        cLastName1 = binding.edtPrimerApellido.text.toString(),
                        cLastName2 = binding.edtSegundoApellido.text.toString(),
                        cId = customers.cId)

                val dialog = DialogConfirmacion()
                dialog.update("¿Estás seguro de editar el cliente:" + customersUpdateRequest.cName1.uppercase() + " " + customersUpdateRequest.cLastName1.uppercase() + " con documento: " + customersUpdateRequest.cDocument + "?")
                dialog.show(
                    requireActivity().supportFragmentManager.beginTransaction(),
                    DialogNotificaciones::class.java.name
                )

                dialog.clickedConfirmar.observe(this, Observer {

                    binding.loading.visibility = View.VISIBLE
                    binding.menuEnvio.visibility = View.GONE
                    binding.loading.startShimmer()
                    viewModel.updateCustomers(customersUpdateRequest)

                })


            } else dialogBase(getString(R.string.campos))

        }

        viewModel.editarModel.observe(this, { OK ->

            Handler(Looper.getMainLooper()).postDelayed({

                binding.edtDocumento.text.clear()
                binding.edtPrimerNombre.text.clear()
                binding.edtSegundoNombre.text.clear()
                binding.edtPrimerApellido.text.clear()
                binding.edtSegundoApellido.text.clear()

                dialogBase(getString(R.string.actualizado))
                findNavController().navigate(
                    R.id.action_editCustomersFragment_to_customersFragment
                )
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
            binding.menuEnvio.visibility = View.VISIBLE
        }
    }

}