package com.onoff.onoffapp.data.network.service

import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.data.model.CustomersModelResponse
import com.onoff.onoffapp.data.model.DeleteModelResponse
import com.onoff.onoffapp.data.model.SetCustomersModelResponse
import com.onoff.onoffapp.data.network.apiClient.CustomersApiClient
import com.onoff.onoffapp.domain.model.CustomersRequest
import com.onoff.onoffapp.domain.model.CustomersUpdateRequest
import com.onoff.onoffapp.util.FlagConstants.OK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class CustomersService @Inject constructor(private val apiClient: CustomersApiClient) {

    suspend fun getCustomersFromApi(): CustomersModelResponse {
        return withContext(Dispatchers.IO) {
            val reponse = apiClient.getCustomers()
            when (reponse.code()) {
                OK -> CustomersModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), customers = reponse.body()!!.customers)

                else -> CustomersModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = false
                    ), customers = emptyList())
            }
        }
    }

    suspend fun getQueryCustomersFromApi(document:Long): CustomersModelResponse {
        return withContext(Dispatchers.IO) {
            val reponse = apiClient.getQueryUser(document)
            when (reponse.code()) {
                OK -> CustomersModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), customers = reponse.body()!!.customers)

                else -> CustomersModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = false
                    ), customers = emptyList())
            }
        }
    }

    suspend fun deleteFromApi(cId:Int): DeleteModelResponse {
        return withContext(Dispatchers.IO) {
            val reponse = apiClient.deleteQueryCustomers(cId)
            when (reponse.code()) {
                OK -> DeleteModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), msg = reponse.body()!!.msg)

                else -> DeleteModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = false
                    ), msg = "error")
            }
        }
    }

    suspend fun updateFromApi(customersUpdateRequest: CustomersUpdateRequest): DeleteModelResponse {
        return withContext(Dispatchers.IO) {
            val reponse = apiClient.updateQueryCustomers(customersUpdateRequest)
            when (reponse.code()) {
                OK -> DeleteModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), msg = reponse.body()!!.msg)

                else -> DeleteModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = false
                    ), msg = "error")
            }
        }
    }

    suspend fun setCustomersApi(customersRequest: CustomersRequest): SetCustomersModelResponse {
        return withContext(Dispatchers.IO) {
            val response = apiClient.setCustomers(customersRequest)
            when (response.code()) {
                OK -> SetCustomersModelResponse(
                    apiFailed = ApiFailed(
                        code = response.code(),
                        message = response.message().toString(),
                        success = true
                    ), cpId = response.body()!!.cpId,
                    cId = response.body()!!.cId,
                    msgId = response.body()!!.msgId,
                    msgStr = response.body()!!.msgStr)

                else -> SetCustomersModelResponse(
                    apiFailed = ApiFailed(
                        code = response.code(),
                        message = response.message().toString(),
                        success = false
                    ), cpId = response.body()!!.cpId,
                    cId = response.body()!!.cId,
                    msgId = response.body()!!.msgId,
                    msgStr = response.body()!!.msgStr)

            }
        }
    }
}