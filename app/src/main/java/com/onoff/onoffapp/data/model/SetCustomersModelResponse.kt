package com.onoff.onoffapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class SetCustomersModelResponse(
    @SerializedName("cpId") val cpId: Int,
    @SerializedName("cId") val cId: Int,
    @SerializedName("msgId") val msgId: Int,
    @SerializedName("msgStr") val msgStr: String,
    var apiFailed: ApiFailed
)