package com.onoff.onoffapp.domain.usecase

import com.onoff.onoffapp.data.database.entities.CustomersEntity
import com.onoff.onoffapp.data.database.entities.toDataBase
import com.onoff.onoffapp.data.repository.CustomersRepository
import com.onoff.onoffapp.domain.model.*
import com.onoff.onoffapp.util.FlagConstants.OK_RESPONSE
import javax.inject.Inject

class CustomersUseCase @Inject constructor(private val customersRepository: CustomersRepository) {
    suspend fun getDataBaseFromApi(): CustomersResponse {
        val response = customersRepository.getCustomersFromApi()
        return if (response.apiFailed.success) {
            customersRepository.insertList(response.customers.map { it.toDataBase() })
            response
        } else customersRepository.getCustomersDataBase(response.apiFailed)
    }

    suspend fun getDataBase(): List<Customers> {
        return customersRepository.getCustomersDataBase()
    }

    suspend fun getQueryDataBase(query: String): List<Customers> {
        return customersRepository.getQueryCustomersDataBase(query)
    }

    suspend fun getQueryFromApi(document: Long): CustomersResponse {
        val response = customersRepository.getQueryCustomersFromApi(document)
        return if (response.apiFailed.success) {
            response
        } else customersRepository.getQueryDataBase(response.apiFailed)
    }

    suspend fun deleteQueryFromApi(cId: Int): DeleteResponse {
        val response = customersRepository.deleteQueryCustomersFromApi(cId)
        if (response.msg == OK_RESPONSE) customersRepository.deleteQuery(cId)
        return response
    }

    suspend fun updateQueryFromApi(customersUpdateRequest: CustomersUpdateRequest): DeleteResponse {
        val response = customersRepository.updateQueryCustomersFromApi(customersUpdateRequest)
        if (response.msg == OK_RESPONSE) customersRepository.update(customersUpdateRequest.toDataBase())
        return response
    }

    suspend fun setCustomers(customersRequest: CustomersRequest): SetCustomersResponse {
        val response = customersRepository.setCustomersApi(customersRequest)
        if (response.apiFailed.success) customersRepository.insert(CustomersEntity(
            cId = response.cId,
            cDocument = customersRequest.cDocument,
            cName1 = customersRequest.cName1,
            cName2 = customersRequest.cName2,
            cLastName1 = customersRequest.cLastName1,
            cLastName2 = customersRequest.cLastName2,
            isSync = true
        ))
        return response
    }


}