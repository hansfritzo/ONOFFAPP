package com.onoff.onoffapp.data.network.apiClient

import com.onoff.onoffapp.data.model.UserModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by hans fritz ortega on 20/06/02.
 */
interface UserApiClient {

    @GET("useronoff/{user}/{password}")

    suspend fun getUser(
        @Path("user") user: String,
        @Path("password") state: String,
    ): Response<UserModelResponse>

}