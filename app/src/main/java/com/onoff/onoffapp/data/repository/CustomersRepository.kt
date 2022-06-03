package com.onoff.onoffapp.data.repository

import com.onoff.onoffapp.data.database.dao.CustomersDao
import com.onoff.onoffapp.data.database.entities.CustomersEntity
import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.data.network.service.CustomersService
import com.onoff.onoffapp.domain.model.*
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class CustomersRepository @Inject constructor(
    private val customersService: CustomersService, private val customersDao: CustomersDao,
) {

    suspend fun getCustomersFromApi(): CustomersResponse {
        val response = customersService.getCustomersFromApi()
        return if (response.apiFailed.success)
            CustomersResponse(
                customers = response.customers.map { it.toDomain() },
                apiFailed = response.apiFailed
            )
        else CustomersResponse(customers = emptyList(), apiFailed = response.apiFailed)
    }


    suspend fun getQueryCustomersFromApi(document: Long): CustomersResponse {
        val response = customersService.getQueryCustomersFromApi(document)
        return if (response.apiFailed.success)
            CustomersResponse(
                customers = response.customers.map { it.toDomain() },
                apiFailed = response.apiFailed
            )
        else CustomersResponse(customers = emptyList(), apiFailed = response.apiFailed)
    }

    suspend fun deleteQueryCustomersFromApi(cId: Int): DeleteResponse {
        val response = customersService.deleteFromApi(cId)
        return if (response.apiFailed.success)
            DeleteResponse(
                msg = response.msg,
                apiFailed = response.apiFailed
            )
        else DeleteResponse(msg = "error", apiFailed = response.apiFailed)
    }

    suspend fun updateQueryCustomersFromApi(customersUpdateRequest: CustomersUpdateRequest): DeleteResponse {
        val response = customersService.updateFromApi(customersUpdateRequest)
        return if (response.apiFailed.success)
            DeleteResponse(
                msg = response.msg,
                apiFailed = response.apiFailed
            )
        else DeleteResponse(msg = "error", apiFailed = response.apiFailed)
    }

    suspend fun setCustomersApi(customersRequest: CustomersRequest): SetCustomersResponse {
        val response = customersService.setCustomersApi(customersRequest)
        return if (response.apiFailed.success)
            SetCustomersResponse(
                cpId = response.cpId,
                cId = response.cId,
                msgId = response.msgId,
                msgStr = response.msgStr,
                apiFailed = response.apiFailed)
        else SetCustomersResponse(cpId = response.cpId,
            cId = response.cId,
            msgId = response.msgId,
            msgStr = response.msgStr, apiFailed = response.apiFailed)
    }

    suspend fun getCustomersDataBase(apiFailed: ApiFailed): CustomersResponse {
        val response = customersDao.getCustomers()
        return CustomersResponse(
            apiFailed = apiFailed,
            customers = response.map { it.toDomain() })
    }

    suspend fun getQueryDataBase(apiFailed: ApiFailed): CustomersResponse {
        return CustomersResponse(
            apiFailed = apiFailed,
            customers = emptyList())
    }

    suspend fun getCustomersDataBase(): List<Customers> {
        val response = customersDao.getCustomers()
        return response.map { it.toDomain() }
    }

    suspend fun getQueryCustomersDataBase(query: String): List<Customers> {
        val response = customersDao.getQueryCustomers("%$query%")
        return response.map { it.toDomain() }
    }
    suspend fun insertList(customers: List<CustomersEntity>) {
        customersDao.insert(customers)
    }
    suspend fun insert(customers: CustomersEntity) {
        customersDao.insertCustomers(customers)
    }


    suspend fun update(customers: CustomersEntity) {
        customersDao.update(customers)
    }
    suspend fun deleteQuery(cId:Int) {
        customersDao.deleteQuery(cId)
    }
}