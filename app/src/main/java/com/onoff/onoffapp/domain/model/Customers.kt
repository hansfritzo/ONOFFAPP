package com.onoff.onoffapp.domain.model

import com.onoff.onoffapp.data.database.entities.CustomersEntity
import com.onoff.onoffapp.data.model.CustomersModel
import java.io.Serializable

/**
 * Created by hans fritz ortega on 20/06/02.
 */

data class Customers(
    val cDocument: Long,
    val cName1: String,
    val cName2: String,
    val cLastName1: String,
    val cLastName2: String,
    val isSync: Boolean,
    val cId: Int,
) : Serializable

fun CustomersModel.toDomain() = Customers(cDocument, cName1, cName2, cLastName1, cLastName2, isSync,cId)
fun CustomersEntity.toDomain() =  Customers(cDocument, cName1, cName2, cLastName1, cLastName2, isSync,cId)


