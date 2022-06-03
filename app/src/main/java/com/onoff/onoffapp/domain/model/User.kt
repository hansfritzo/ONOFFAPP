package com.onoff.onoffapp.domain.model

import com.onoff.onoffapp.data.database.entities.UserEntity
import com.onoff.onoffapp.data.model.UserModel

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class User(
    val idUser: Int,
    val password: String,
    val user: String,
    val name: String,
)

fun UserModel.toDomain() = User(idUser, password, user,name)
fun UserEntity.toDomain() = User(idUser, password, user,name)


