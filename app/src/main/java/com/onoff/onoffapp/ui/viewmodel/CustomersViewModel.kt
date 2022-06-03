package com.onoff.onoffapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.domain.model.*
import com.onoff.onoffapp.domain.usecase.CustomersPhonesUseCase
import com.onoff.onoffapp.domain.usecase.CustomersUseCase
import com.onoff.onoffapp.domain.usecase.LogUseCase
import com.onoff.onoffapp.domain.usecase.UserUseCase
import com.onoff.onoffapp.util.FlagConstants.OK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val customersUseCase: CustomersUseCase,
    private val customersPhonesUseCase: CustomersPhonesUseCase,
    private val userUseCase: UserUseCase,
    private val logUseCase: LogUseCase,
) :
    ViewModel() {
    val customersModel = MutableLiveData<List<Customers>>()
    val phonesModel = MutableLiveData<CustomersPhonesRequest>()
    val customersPhonesModel = MutableLiveData<List<CustomersPhones>>()
    val empyModel = MutableLiveData<Boolean>()
    val phoneAllModel = MutableLiveData<Boolean>()
    val eliminarModel = MutableLiveData<Boolean>()
    val apiFailedModel = MutableLiveData<ApiFailed>()

    val userModel = MutableLiveData<User>()

    fun getUserDataBase() {
        viewModelScope.launch {
            val result = userUseCase.getUserDataBase()
            if (result.isNotEmpty()) userModel.postValue(result[0])
        }
    }

    fun getQueryDataBase(query: String) {
        viewModelScope.launch {
            val result = customersUseCase.getQueryDataBase(query)
            customersModel.postValue(result)
        }
    }

    fun getCustomersFromApi() {
        viewModelScope.launch {
            val resultdatabase = customersUseCase.getDataBase()
            if (resultdatabase.isNotEmpty()) customersModel.postValue(resultdatabase)
            val result = customersUseCase.getDataBaseFromApi()
            when (result.apiFailed.code) {
                OK -> {
                    if (result.customers.isNotEmpty()) customersModel.postValue(result.customers)
                    else empyModel.postValue(true)
                }
                else -> apiFailedModel.postValue(result.apiFailed)
            }
        }
    }

    fun setLog(cDocument: Long) {
        viewModelScope.launch {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val data = sdf.format(Date())
            logUseCase.insert(Log(logDate = data,
                logDescripcion = "No se pueden agregar mas de 5 números de teléfono al cliente con" +
                        " documento $cDocument ",
                isSync = true))
        }
    }

    fun getCustomersPhonesFromApi() {

        viewModelScope.launch {
            val result = customersPhonesUseCase.getFromapi()
            when (result.apiFailed.code) {
                OK -> {

                }
                else -> apiFailedModel.postValue(result.apiFailed)
            }
        }

    }

    fun getCustomersDataBase() {
        viewModelScope.launch {
            val result = customersUseCase.getDataBase()
            customersModel.postValue(result)
        }
    }

    fun getCustomersPhonesDataBase(cId: Int) {
        viewModelScope.launch {
            val result = customersPhonesUseCase.getDataBase(cId)
            customersPhonesModel.postValue(result)
        }
    }

    fun deleteCustomersFromApi(cId: Int, cDocument: Long) {
        viewModelScope.launch {
            val result = customersUseCase.deleteQueryFromApi(cId)
            when (result.apiFailed.code) {
                OK -> {
                    if (result.msg == "OK") {
                        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
                        val data = sdf.format(Date())
                        logUseCase.insert(Log(logDate = data,
                            logDescripcion = "Cliente con número de documento $cDocument eliminado correctamente",
                            isSync = true))
                        eliminarModel.postValue(true)
                    }
                }
                else -> apiFailedModel.postValue(result.apiFailed)
            }
        }
    }

    fun setCustomersPhones(customersPhonesRequest: CustomersPhonesRequest, cDocument: Long) {
        viewModelScope.launch {

            val customers = customersPhonesUseCase.getQueryFromapi(customersPhonesRequest.cId)
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val data = sdf.format(Date())
            when (customers.apiFailed.code) {
                OK -> {
                    if (customers.customersPhones.isNotEmpty() && customers.customersPhones.size < 5) {
                        val result = customersPhonesUseCase.setCustomers(customersPhonesRequest)
                        when (result.apiFailed.code) {
                            OK -> {
                                logUseCase.insert(Log(logDate = data,
                                    logDescripcion = "Número de teléfono ${customersPhonesRequest.cpPhone}  agregado correctamente al cliente con documento $cDocument ",
                                    isSync = true))
                                phonesModel.postValue(customersPhonesRequest)
                            }
                            else -> apiFailedModel.postValue(result.apiFailed)
                        }
                    } else {

                        logUseCase.insert(Log(logDate = data,
                            logDescripcion = "No se pueden agregar mas de 5 números de teléfono al cliente con documento $cDocument ",
                            isSync = true))
                        phoneAllModel.postValue(true)
                    }

                }
                else -> apiFailedModel.postValue(customers.apiFailed)
            }

        }
    }
}
