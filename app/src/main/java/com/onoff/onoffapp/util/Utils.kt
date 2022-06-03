package com.onoff.onoffapp.util

/**
 * Created by hans fritz ortega on 20/06/02.
 */
object Utils {

    fun minuscula(str: String?): String? {
        return if (str == null || str.isEmpty()) {
            ""
        } else {
            Character.toUpperCase(str[0]).toString() + str.substring(1, str.length).toLowerCase()
        }
    }

}