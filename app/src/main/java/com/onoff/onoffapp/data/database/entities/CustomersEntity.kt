package com.onoff.onoffapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onoff.onoffapp.domain.model.Customers
import com.onoff.onoffapp.domain.model.CustomersUpdateRequest

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Entity(tableName = "customers")
data class CustomersEntity(

    @PrimaryKey()
    @ColumnInfo(name = "cId") val cId: Int,
    @ColumnInfo(name = "cDocument") val cDocument: Long,
    @ColumnInfo(name = "cName1") val cName1: String,
    @ColumnInfo(name = "cName2") val cName2: String,
    @ColumnInfo(name = "cLastName1") val cLastName1: String,
    @ColumnInfo(name = "cLastName2") val cLastName2: String,
    @ColumnInfo(name = "isSync") val isSync: Boolean,
)

fun Customers.toDataBase() = CustomersEntity(
    cDocument = cDocument,
    cName1 = cName1,
    cName2 = cName2,
    cLastName1 = cLastName1,
    cLastName2 = cLastName2,
    isSync = isSync,
    cId = cId
)

fun CustomersUpdateRequest.toDataBase() = CustomersEntity(
    cDocument = cDocument,
    cName1 = cName1,
    cName2 = cName2,
    cLastName1 = cLastName1,
    cLastName2 = cLastName2,
    cId = cId,
    isSync = true
)




