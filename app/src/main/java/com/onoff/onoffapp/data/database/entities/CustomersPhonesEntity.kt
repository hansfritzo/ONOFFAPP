package com.onoff.onoffapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onoff.onoffapp.domain.model.CustomersPhones

/**
 * Created by hans fritz ortega on 20/06/02.
 */
@Entity(tableName = "Customers_Phones")
data class CustomersPhonesEntity(

    @PrimaryKey()
    @ColumnInfo(name = "cpId") val cpId: Int,
    @ColumnInfo(name = "cId") val cId: Int,
    @ColumnInfo(name = "cpPhone") val cpPhone: String,
    @ColumnInfo(name = "isSync") val isSync: Boolean,

    )

fun CustomersPhones.toDataBase() = CustomersPhonesEntity(
    cpId = cpId,
    cId = cId,
    cpPhone = cpPhone,
    isSync = isSync
)




