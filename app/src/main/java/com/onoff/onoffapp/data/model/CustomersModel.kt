package com.onoff.onoffapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hans fritz ortega on 20/06/02.
 */

data class CustomersModel(
    @SerializedName( "cId") val cId: Int,
    @SerializedName("cDocument") val cDocument: Long,
    @SerializedName("cName1") val cName1: String,
    @SerializedName("cName2") val cName2: String,
    @SerializedName("cLastName1") val cLastName1: String,
    @SerializedName("cLastName2") val cLastName2: String,
    @SerializedName("isSync") val isSync: Boolean,
)




