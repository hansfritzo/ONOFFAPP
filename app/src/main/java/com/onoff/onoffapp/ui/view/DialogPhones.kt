package com.onoff.onoffapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.DialogoPhonesBinding
import com.onoff.onoffapp.domain.model.CustomersPhones

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@SuppressLint("Dialogo Notificaciones")
class DialogPhones() : DialogFragment() {
    private var binding: DialogoPhonesBinding? = null
    lateinit var customersPhones: List<CustomersPhones>
    private val adapterCustomersPhones = AdapterCustomersPhones()
    val clickedAgregar = MutableLiveData<Boolean>()
    val maximoModel = MutableLiveData<Boolean>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogoPhonesBinding
            .inflate(inflater, container, false)

        val view = binding!!.root

        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        binding!!.txtNuevo.setOnClickListener {

            if (customersPhones.size < 5) {
                dismiss()
                clickedAgregar.postValue(true)
            } else {

                Toast.makeText(requireContext(),
                    getString(R.string.telefonos),
                    Toast.LENGTH_LONG)
                    .show()
                maximoModel.postValue(true)
            }

        }

        binding!!.txtCancelar.setOnClickListener {
            dismiss()
        }

        adapterCustomersPhones.update(customersPhones)

        binding!!.recyPhones.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding!!.recyPhones.adapter = adapterCustomersPhones
        binding!!.recyPhones.setHasFixedSize(true)

        return view
    }

    override fun onResume() {
        super.onResume()

        val window = dialog!!.window
        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams()

        dialog!!.window!!.setBackgroundDrawableResource(R.color.transparent)

        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        window.setGravity(Gravity.CENTER)
    }

    fun update(customersPhones: List<CustomersPhones>) {
        this.customersPhones = customersPhones
    }

}