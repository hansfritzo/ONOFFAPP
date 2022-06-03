package com.onoff.onoffapp.domain.model

import com.onoff.onoffapp.data.database.entities.LogEntity
import com.onoff.onoffapp.data.model.LogModel

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class Log(
    val logDate: String,
    val logDescripcion: String,
    val isSync: Boolean,
)

fun LogModel.toDomain() = Log(logDate, logDescripcion, isSync)
fun LogEntity.toDomain() = Log(logDate, logDescripcion, isSync)




