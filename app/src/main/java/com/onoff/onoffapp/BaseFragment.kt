package com.onoff.onoffapp

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.onoff.onoffapp.ui.view.DialogNotificaciones
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@AndroidEntryPoint
open class BaseFragment : Fragment() {

    fun toastBase(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
    }

    fun dialogBase(mensaje: String) {
        val dialog = DialogNotificaciones(mensaje)
        dialog.show(
            requireActivity().supportFragmentManager.beginTransaction(),
            DialogNotificaciones::class.java.name
        )
    }

}
