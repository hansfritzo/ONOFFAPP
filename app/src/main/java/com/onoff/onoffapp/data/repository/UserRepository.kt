package com.onoff.onoffapp.data.repository

import com.onoff.onoffapp.data.database.dao.UserDao
import com.onoff.onoffapp.data.database.entities.UserEntity
import com.onoff.onoffapp.data.model.ApiFailed
import com.onoff.onoffapp.data.network.service.UserService
import com.onoff.onoffapp.domain.model.User
import com.onoff.onoffapp.domain.model.UserResponse
import com.onoff.onoffapp.domain.model.toDomain
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class UserRepository @Inject constructor(
    private val userService: UserService, private val userDao: UserDao,
) {

    suspend fun getUserFromApi(user: String, password: String): UserResponse {
        val response = userService.getUserFromApi(user, password)
        return if (response.apiFailed.success)
            UserResponse(
                user = response.useronoff.map { it.toDomain() },
                apiFailed = response.apiFailed
            )
        else UserResponse(user = emptyList(), apiFailed = response.apiFailed)
    }

    suspend fun getUserDataBase(apiFailed: ApiFailed): UserResponse {
        val response = userDao.getUser()
        return UserResponse(
            apiFailed = apiFailed,
            user = response.map { it.toDomain() })
    }

    suspend fun getUserDataBase(): List<User> {
        val response = userDao.getUser()
        return response.map { it.toDomain() }
    }

    suspend fun insertList(userEntity: List<UserEntity>) {
        userDao.insert(userEntity)
    }
}