package com.onoff.onoffapp.data.network.service

import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.data.model.CustomersPhonesModelResponse
import com.onoff.onoffapp.data.model.SetCustomersModelResponse
import com.onoff.onoffapp.data.network.apiClient.CustomersPhonesApiClient
import com.onoff.onoffapp.domain.model.CustomersPhonesRequest
import com.onoff.onoffapp.util.FlagConstants.OK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class CustomerPhonesService @Inject constructor(private val apiClient: CustomersPhonesApiClient) {

    suspend fun getCustomersPhones(): CustomersPhonesModelResponse {
        return withContext(Dispatchers.IO) {
            val reponse = apiClient.getCustomersPhones()
            when (reponse.code()) {
                OK -> CustomersPhonesModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), customersPhones = reponse.body()!!.customersPhones)

                else -> CustomersPhonesModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = false
                    ), customersPhones = emptyList())
            }
        }
    }

    suspend fun getQueryCustomersPhones(cId:Int): CustomersPhonesModelResponse {
        return withContext(Dispatchers.IO) {
            val reponse = apiClient.getQueryCustomersPhone(cId)
            when (reponse.code()) {
                OK -> CustomersPhonesModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = true
                    ), customersPhones = reponse.body()!!.customersPhones)

                else -> CustomersPhonesModelResponse(
                    apiFailed = ApiFailed(
                        code = reponse.code(),
                        message = reponse.message().toString(),
                        success = false
                    ), customersPhones = emptyList())
            }
        }
    }


    suspend fun setCustomersApi(customersPhonesRequest: CustomersPhonesRequest): SetCustomersModelResponse {
        return withContext(Dispatchers.IO) {
            val response = apiClient.setCustomersPhones(customersPhonesRequest)
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