package com.onoff.onoffapp.data.repository

import com.onoff.onoffapp.data.database.dao.LogDao
import com.onoff.onoffapp.data.database.entities.LogEntity
import javax.inject.Inject

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class LogRepository @Inject constructor(private val logDao: LogDao) {

    suspend fun inser(logEntity: LogEntity) {
        logDao.insert(logEntity)
    }
}