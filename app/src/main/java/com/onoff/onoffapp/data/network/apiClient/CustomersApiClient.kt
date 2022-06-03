package com.onoff.onoffapp.data.network.apiClient

import com.onoff.onoffapp.data.model.CustomersModelResponse
import com.onoff.onoffapp.data.model.DeleteModelResponse
import com.onoff.onoffapp.data.model.SetCustomersModelResponse
import com.onoff.onoffapp.domain.model.CustomersRequest
import com.onoff.onoffapp.domain.model.CustomersUpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by hans fritz ortega on 20/06/02.
 */
interface CustomersApiClient {

    @GET("customers")
    suspend fun getCustomers(): Response<CustomersModelResponse>

    @GET("querycustomers/{cDocument}")
    suspend fun getQueryUser(@Path("cDocument") cDocument: Long): Response<CustomersModelResponse>

    @GET("deleteQueryCustomers/{cId}")
    suspend fun deleteQueryCustomers(@Path("cId") cDocument: Int): Response<DeleteModelResponse>

    @POST("setCustomers")
    suspend fun setCustomers(@Body customersRequest: CustomersRequest): Response<SetCustomersModelResponse>

    @POST("updateQueryCustomers")
    suspend fun updateQueryCustomers(@Body customersUpdateRequest: CustomersUpdateRequest): Response<DeleteModelResponse>

}