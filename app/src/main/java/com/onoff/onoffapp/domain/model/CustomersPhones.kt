package com.onoff.onoffapp.domain.model

import com.onoff.onoffapp.data.database.entities.CustomersPhonesEntity
import com.onoff.onoffapp.data.model.CustomersPhonesModel

/**
 * Created by hans fritz ortega on 20/06/02.
 */

data class CustomersPhones(
    val cpId: Int,
    val cId: Int,
    val cpPhone: String,
    val isSync: Boolean,
)

fun CustomersPhonesEntity.toDomain() = CustomersPhones(cpId, cId, cpPhone, isSync)
fun CustomersPhonesModel.toDomain() = CustomersPhones(cpId, cId, cpPhone, isSync)



