package com.onoff.onoffapp.data.network.service

import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.data.model.UserModelResponse
import com.onoff.onoffapp.data.network.apiClient.UserApiClient
import com.onoff.onoffapp.util.FlagConstants.OK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class UserService @Inject constructor(private val userApiClient: UserApiClient) {

    suspend fun getUserFromApi(user: String, password: String): UserModelResponse {
        return withContext(Dispatchers.IO) {
            val reponse = userApiClient.getUser(user, password)
            when (reponse.code()) {
                OK -> UserModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), useronoff = reponse.body()!!.useronoff)

                else -> UserModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), useronoff = emptyList())
            }
        }
    }
}