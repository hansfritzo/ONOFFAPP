package com.onoff.onoffapp.domain.usecase

import com.onoff.onoffapp.data.database.entities.CustomersPhonesEntity
import com.onoff.onoffapp.data.database.entities.toDataBase
import com.onoff.onoffapp.data.repository.CustomersPhonesRepository
import com.onoff.onoffapp.domain.model.*
import javax.inject.Inject

class CustomersPhonesUseCase @Inject constructor(private val customersPhonesRepository: CustomersPhonesRepository) {
    suspend fun getFromapi(): CustomersPhonesResponse {
        val response = customersPhonesRepository.getCustomersPhonesFromApi()
        return if (response.apiFailed.success) {
            customersPhonesRepository.insertList(response.customersPhones.map { it.toDataBase() })
            response
        } else customersPhonesRepository.getUserDataBase(response.apiFailed)
    }

    suspend fun getQueryFromapi(cId:Int): CustomersPhonesResponse {
        val response = customersPhonesRepository.getQueryCustomersPhonesFromApi(cId)
        return if (response.apiFailed.success) {
            customersPhonesRepository.insertList(response.customersPhones.map { it.toDataBase() })
            response
        } else customersPhonesRepository.getUserDataBase(response.apiFailed)
    }

    suspend fun getDataBase(cId: Int): List<CustomersPhones> {
        return customersPhonesRepository.getDataBase(cId)
    }

    suspend fun setCustomers(customersPhonesRequest: CustomersPhonesRequest): SetCustomersResponse {
        val response = customersPhonesRepository.setCustomersApi(customersPhonesRequest)
        if (response.msgId == 1) customersPhonesRepository.insert(CustomersPhonesEntity(cpId = response.cpId,
            cId = response.cId,
            cpPhone = customersPhonesRequest.cpPhone,
            isSync = customersPhonesRequest.isSync))
        return response
    }
}