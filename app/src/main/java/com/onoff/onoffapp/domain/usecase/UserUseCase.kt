package com.onoff.onoffapp.domain.usecase

import com.onoff.onoffapp.data.database.entities.toDataBase
import com.onoff.onoffapp.data.repository.UserRepository
import com.onoff.onoffapp.domain.model.User
import com.onoff.onoffapp.domain.model.UserResponse
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun getUserFromapi(usar: String, passaword: String): UserResponse {
        val response = userRepository.getUserFromApi(usar, passaword)
        return if (response.apiFailed.success) {
            userRepository.insertList(response.user.map { it.toDataBase() })
            response
        } else userRepository.getUserDataBase(response.apiFailed)
    }

    suspend fun getUserDataBase(): List<User> {
        val response = userRepository.getUserDataBase()
        return if (response.isNotEmpty()) {
            response
        } else emptyList()
    }
}