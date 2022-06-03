package com.onoff.onoffapp.domain.model

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class CustomersUpdateRequest(
    val cDocument: Long,
    val cId: Int,
    val cName1: String,
    val cName2: String="",
    val cLastName1: String,
    val cLastName2: String=""
)
