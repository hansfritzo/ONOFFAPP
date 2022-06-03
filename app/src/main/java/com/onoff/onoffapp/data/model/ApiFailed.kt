package com.onoff.onoffapp.data.model

import java.io.Serializable

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class ApiFailed(

    val code: Int,
    val message: String,
    val success: Boolean
) : Serializable
