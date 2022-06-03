package com.onoff.onoffapp.data.network.apiClient

import com.onoff.onoffapp.data.model.CustomersPhonesModelResponse
import com.onoff.onoffapp.data.model.SetCustomersModelResponse
import com.onoff.onoffapp.domain.model.CustomersPhonesRequest
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by hans fritz ortega on 20/06/02.
 */
interface CustomersPhonesApiClient {

    @GET("customersPhones")
    suspend fun getCustomersPhones(): Response<CustomersPhonesModelResponse>

    @GET("querycustomersphones/{cId}")
    suspend fun getQueryCustomersPhone(@Path("cId") cId: Int): Response<CustomersPhonesModelResponse>

    @POST("setCustomersPhones")
    suspend fun setCustomersPhones(@Body customersPhonesRequest: CustomersPhonesRequest): Response<SetCustomersModelResponse>

}