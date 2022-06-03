package com.onoff.onoffapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class LogModel(
    @SerializedName("logDate") val logDate: String,
    @SerializedName("logDescripcion") val logDescripcion: String,
    @SerializedName("isSync") val isSync: Boolean
)





