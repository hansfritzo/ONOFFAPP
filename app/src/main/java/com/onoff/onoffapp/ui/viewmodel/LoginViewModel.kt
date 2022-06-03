package com.onoff.onoffapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.domain.usecase.UserUseCase
import com.onoff.onoffapp.util.FlagConstants.OK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    val userModel = MutableLiveData<Boolean>()
    val apiFailedModel = MutableLiveData<ApiFailed>()
    fun getUserFromApi(user: String, password: String) {
        viewModelScope.launch {
            val result = userUseCase.getUserFromapi(user, password)
            when (result.apiFailed.code) {
                OK -> {
                    if (result.user.isNotEmpty()) userModel.postValue(true)
                    else userModel.postValue(false)
                }
                else -> apiFailedModel.postValue(result.apiFailed)
            }
        }
    }

}