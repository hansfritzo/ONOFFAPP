package com.onoff.onoffapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoff.onoffapp.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@HiltViewModel
class SplahsViewModel @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModel() {
    val userModel = MutableLiveData<Boolean>()
    fun getUserDataBase() {
        viewModelScope.launch {
            val result = userUseCase.getUserDataBase()
            if (result.isNotEmpty()) userModel.postValue(true)
            else userModel.postValue(false)
        }
    }
}
