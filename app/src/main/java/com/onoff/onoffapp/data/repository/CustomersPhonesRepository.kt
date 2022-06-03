package com.onoff.onoffapp.data.repository

import com.onoff.onoffapp.data.database.dao.CustomersPhonesDao
import com.onoff.onoffapp.data.database.entities.CustomersPhonesEntity
import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.data.network.service.CustomerPhonesService
import com.onoff.onoffapp.domain.model.*
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class CustomersPhonesRepository @Inject constructor(
    private val customerPhonesService: CustomerPhonesService,
    private val customersPhonesDao: CustomersPhonesDao,
) {

    suspend fun getCustomersPhonesFromApi(): CustomersPhonesResponse {
        val response = customerPhonesService.getCustomersPhones()
        return if (response.apiFailed.success)
            CustomersPhonesResponse(
                customersPhones = response.customersPhones.map { it.toDomain() },
                apiFailed = response.apiFailed
            )
        else CustomersPhonesResponse(customersPhones = emptyList(), apiFailed = response.apiFailed)
    }


    suspend fun getQueryCustomersPhonesFromApi(cId:Int): CustomersPhonesResponse {
        val response = customerPhonesService.getQueryCustomersPhones(cId)
        return if (response.apiFailed.success)
            CustomersPhonesResponse(
                customersPhones = response.customersPhones.map { it.toDomain() },
                apiFailed = response.apiFailed
            )
        else CustomersPhonesResponse(customersPhones = emptyList(), apiFailed = response.apiFailed)
    }

    suspend fun getUserDataBase(apiFailed: ApiFailed): CustomersPhonesResponse {
        val response = customerPhonesService.getCustomersPhones()
        return CustomersPhonesResponse(
            apiFailed = apiFailed,
            customersPhones = response.customersPhones.map { it.toDomain() })
    }

    suspend fun getDataBase(cId:Int): List<CustomersPhones> {
        val response = customersPhonesDao.getCustomerPhones(cId)
        return response.map { it.toDomain() }
    }
    suspend fun insertList(customersPhonesEntity: List<CustomersPhonesEntity>) {
        customersPhonesDao.insert(customersPhonesEntity)
    }

    suspend fun insert(customersPhonesEntity: CustomersPhonesEntity) {
        customersPhonesDao.insertPhone(customersPhonesEntity)
    }

    suspend fun setCustomersApi(customersPhonesRequest: CustomersPhonesRequest): SetCustomersResponse {
        val response = customerPhonesService.setCustomersApi(customersPhonesRequest)
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
}