package com.onoff.onoffapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onoff.onoffapp.domain.model.Log

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Entity(tableName = "log")
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "logId") val logId: Int = 0,
    @ColumnInfo(name = "logDate") val logDate: String,
    @ColumnInfo(name = "logDescripcion") val logDescripcion: String,
    @ColumnInfo(name = "isSync") val isSync: Boolean,
)

fun Log.toDataBase() = LogEntity(
    logDate = logDate,
    logDescripcion = logDescripcion,
    isSync = isSync
)



