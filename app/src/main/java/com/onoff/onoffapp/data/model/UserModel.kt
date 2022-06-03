package com.onoff.onoffapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class UserModel(
    @SerializedName("idUser") val idUser: Int,
    @SerializedName("password") val password: String,
    @SerializedName("user") val user: String,
    @SerializedName("name") val name: String
)



