package com.onoff.onoffapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.domain.model.CustomersUpdateRequest
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
class EditCustomersViewModel @Inject constructor(private val customersUseCase: CustomersUseCase, private val logUseCase: LogUseCase) :
    ViewModel() {
    val empyModel = MutableLiveData<Boolean>()
    val editarModel = MutableLiveData<Boolean>()
    val apiFailedModel = MutableLiveData<ApiFailed>()

    fun updateCustomers(customersUpdateRequest: CustomersUpdateRequest) {
        viewModelScope.launch {
            val result = customersUseCase.updateQueryFromApi(customersUpdateRequest)
            when (result.apiFailed.code) {
                OK -> {
                    if (result.msg == "OK"){
                        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
                        val data = sdf.format(Date())
                        logUseCase.insert(Log(logDate = data,
                            logDescripcion = "Cliente con número de documento " + customersUpdateRequest.cDocument + " editado correctamente",
                            isSync = true))
                        editarModel.postValue(true)
                    }

                    else empyModel.postValue(true)
                }
                else -> apiFailedModel.postValue(result.apiFailed)
            }
        }
    }
}
