package com.onoff.onoffapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onoff.onoffapp.data.database.dao.CustomersDao
import com.onoff.onoffapp.data.database.dao.CustomersPhonesDao
import com.onoff.onoffapp.data.database.dao.LogDao
import com.onoff.onoffapp.data.database.dao.UserDao
import com.onoff.onoffapp.data.database.entities.CustomersEntity
import com.onoff.onoffapp.data.database.entities.CustomersPhonesEntity
import com.onoff.onoffapp.data.database.entities.LogEntity
import com.onoff.onoffapp.data.database.entities.UserEntity

@Database(
    entities = [UserEntity::class, CustomersEntity::class, CustomersPhonesEntity::class, LogEntity::class],
    version = 9
)
abstract class OnOffDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun customersDao(): CustomersDao
    abstract fun customersPhonesDao(): CustomersPhonesDao
    abstract fun logDao(): LogDao
}