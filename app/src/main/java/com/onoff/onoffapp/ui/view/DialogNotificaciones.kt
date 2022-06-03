package com.onoff.onoffapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.onoff.onoffapp.R
import com.onoff.onoffapp.databinding.DialogoNotificacionesBinding

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@SuppressLint("Dialogo Notificaciones")
class DialogNotificaciones
    (private val mensaje: String) : DialogFragment() {
    private var binding: DialogoNotificacionesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogoNotificacionesBinding
            .inflate(inflater, container, false)

        val view = binding!!.root

        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)

        binding!!.txtMensaje.text = mensaje
        binding!!.txtContinuar.setOnClickListener { dismiss() }

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

}