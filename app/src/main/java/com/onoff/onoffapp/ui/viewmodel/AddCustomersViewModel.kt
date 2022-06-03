package com.onoff.onoffapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.domain.model.CustomersRequest
import com.onoff.onoffapp.domain.model.Log
import com.onoff.onoffapp.domain.usecase.CustomersUseCase
import com.onoff.onoffapp.domain.usecase.LogUseCase
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
class AddCustomersViewModel @Inject constructor(
    private val customersUseCase: CustomersUseCase,
    private val logUseCase: LogUseCase
) :
    ViewModel() {
    val customersModel = MutableLiveData<CustomersRequest>()
    val cunstoQueryModel = MutableLiveData<CustomersRequest>()
    val apiFailedModel = MutableLiveData<ApiFailed>()

    fun setCustomers(customersRequest: CustomersRequest) {
        viewModelScope.launch {
            val customers = customersUseCase.getQueryFromApi(customersRequest.cDocument)
            when (customers.apiFailed.code) {
                OK -> {
                    if (customers.customers.isEmpty()) {
                        val result = customersUseCase.setCustomers(customersRequest)
                        when (result.apiFailed.code) {
                            OK -> {
                                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
                                val data = sdf.format(Date())
                                logUseCase.insert(Log(logDate = data,
                                    logDescripcion = "Cliente con número de documento " + customersRequest.cDocument + " creado correctamente",
                                    isSync = true))
                                customersModel.postValue(customersRequest)
                            }
                            else -> apiFailedModel.postValue(result.apiFailed)
                        }
                    } else cunstoQueryModel.postValue(customersRequest)
                }
                else -> apiFailedModel.postValue(customers.apiFailed)
            }
        }
    }
}
