package com.onoff.onoffapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hans fritz ortega on 20/06/02.
 */

data class CustomersPhonesModel(
    @SerializedName("cpId") val cpId:Int,
    @SerializedName( "cId") val cId: Int,
    @SerializedName("cpPhone") val cpPhone: String,
    @SerializedName("isSync") val isSync: Boolean
    )




