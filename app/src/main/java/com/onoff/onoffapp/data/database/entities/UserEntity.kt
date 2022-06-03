package com.onoff.onoffapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onoff.onoffapp.domain.model.User

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "idUser") val idUser: Int,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "name") val name: String,
)

fun User.toDataBase() = UserEntity(
    idUser = idUser,
    password = password,
    user = user,
    name = name
)



