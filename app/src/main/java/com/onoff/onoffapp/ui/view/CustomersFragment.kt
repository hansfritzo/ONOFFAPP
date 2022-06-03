package com.onoff.onoffapp.ui.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.onoff.onoffapp.BaseFragment
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.CustomersFragmentBinding
import com.onoff.onoffapp.domain.model.Customers
import com.onoff.onoffapp.domain.model.CustomersPhonesRequest
import com.onoff.onoffapp.ui.viewmodel.CustomersViewModel
import com.onoff.onoffapp.util.FlagConstants.TIME_LOADING
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
class CustomersFragment : BaseFragment() {

    private lateinit var viewModel: CustomersViewModel
    private lateinit var binding: CustomersFragmentBinding
    private lateinit var customers: Customers
    private val adapterCustomers = AdapterCustomers()
    val dialogConfirmacion = DialogConfirmacion()
    val dialogPhones = DialogPhones()
    val dialogAddPhone = DialogAddPhone()
    val dialog = DialogMenuCliente()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = CustomersFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CustomersViewModel::class.java)

        binding.loading.visibility = View.VISIBLE
        binding.recyCustomers.visibility = View.GONE
        binding.loading.startShimmer()

        viewModel.getCustomersFromApi()
        viewModel.getUserDataBase()
        viewModel.getCustomersPhonesFromApi()

        val id: Int = binding.search.context.resources
            .getIdentifier("android:id/search_src_text", null, null)

        val editBusqueda = binding.search.findViewById<View>(id) as EditText

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editBusqueda.setTextColor(requireActivity().getColor(R.color.black))
        }

        editBusqueda.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int,
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int,
            ) {

                Handler(Looper.getMainLooper()).postDelayed({

                    viewModel.getQueryDataBase(s.toString())

                },
                    TIME_LOADING
                )
            }
        })
        viewModel.userModel.observe(this, Observer { user ->

            binding.txtUsuario.text = "Hola," + user.name

        })
        viewModel.customersModel.observe(this, { customers ->

            loading()
            adapterCustomers.update(customers)

            binding.recyCustomers.layoutManager = GridLayoutManager(
                requireContext(),
                1,
                LinearLayoutManager.VERTICAL,
                false
            )

            binding.recyCustomers.adapter = adapterCustomers
            binding.recyCustomers.setHasFixedSize(true)


        })

        adapterCustomers.menuClicked.observe(this, { customers ->
            this.customers = customers
            dialog.show(
                requireActivity().supportFragmentManager.beginTransaction(),
                DialogMenuCliente::class.java.name
            )
        })

        adapterCustomers.verMasClicked.observe(this, { customers ->
            this.customers = customers
            viewModel.getCustomersPhonesDataBase(customers.cId)
        })

        viewModel.customersPhonesModel.observe(this, Observer { customersPhones ->

            dialogPhones.update(customersPhones)
            dialogPhones.show(
                requireActivity().supportFragmentManager.beginTransaction(),
                DialogPhones::class.java.name
            )

        })

        dialogPhones.maximoModel.observe(this, Observer {
            viewModel.setLog(customers.cDocument)
        })


        dialogPhones.clickedAgregar.observe(this, Observer {

            dialogAddPhone.show(
                requireActivity().supportFragmentManager.beginTransaction(),
                DialogAddPhone::class.java.name
            )

        })

        dialogAddPhone.clickedEnviar.observe(this, Observer { phone ->

            viewModel.setCustomersPhones(
                CustomersPhonesRequest(cId = customers.cId,
                    cpPhone = phone,
                    isSync = true), customers.cDocument)
        })

        dialogAddPhone

        viewModel.phonesModel.observe(this, Observer {
            viewModel.getCustomersPhonesDataBase(customers.cId)
        })

        viewModel.empyModel.observe(this, Observer {
            loading()
            binding.recyCustomers.visibility = View.GONE
            binding.menuempy.visibility = View.VISIBLE

        })

        viewModel.apiFailedModel.observe(this, { apiFailed ->
            dialogBase(apiFailed.message)
            loading()
        })
        viewModel.phoneAllModel.observe(this, Observer {
            toastBase(getString(R.string.telefonos))
            viewModel.getCustomersPhonesDataBase(customers.cId)
        })

        dialog.clickedEliminar.observe(this, Observer {
            dialogConfirmacion.update("¿Estás seguro de eliminar el cliente:" + customers.cName1.uppercase() + " " + customers.cLastName1.uppercase() + " con documento: " + customers.cDocument + "?")
            dialog.dismiss()
            dialogConfirmacion.show(
                requireActivity().supportFragmentManager.beginTransaction(),
                DialogConfirmacion::class.java.name
            )
        })

        dialog.clickedEditar.observe(this, Observer {

            val bundle = Bundle()
            bundle.putSerializable("customers", customers)

            findNavController().navigate(
                R.id.action_customersFragment_to_editCustomersFragment,
                bundle
            )

        })

        dialogConfirmacion.clickedConfirmar.observe(this, Observer {
            dialogConfirmacion.dismiss()
            viewModel.deleteCustomersFromApi(customers.cId, customers.cDocument)

        })

        viewModel.eliminarModel.observe(this, Observer {
            dialogBase(getString(R.string.eliminado))
            viewModel.getCustomersDataBase()

        })

    }

    private fun loading() {
        if (binding.loading.isShimmerVisible) {
            binding.loading.stopShimmer()
            binding.loading.visibility = View.GONE
            binding.recyCustomers.visibility = View.VISIBLE
        }
    }
}