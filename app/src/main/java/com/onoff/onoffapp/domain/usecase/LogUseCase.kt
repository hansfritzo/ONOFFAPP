package com.onoff.onoffapp.domain.usecase

import com.onoff.onoffapp.data.database.entities.toDataBase
import com.onoff.onoffapp.data.repository.LogRepository
import com.onoff.onoffapp.domain.model.Log
import javax.inject.Inject

class LogUseCase @Inject constructor(private val logRepository: LogRepository) {
    suspend fun insert(log: Log) {
        logRepository.inser(log.toDataBase())
    }
}