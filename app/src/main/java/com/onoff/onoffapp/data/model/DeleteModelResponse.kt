package com.onoff.onoffapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class DeleteModelResponse(
    @SerializedName("msg") val msg: String,
    val apiFailed: ApiFailed
)



