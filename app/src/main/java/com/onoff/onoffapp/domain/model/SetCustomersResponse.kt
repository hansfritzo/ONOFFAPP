package com.onoff.onoffapp.domain.model

import com.onoff.onoffapp.data.model.ApiFailed

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class SetCustomersResponse(
    val cpId: Int,
    val cId: Int,
    val msgId: Int,
    val msgStr: String,
    val apiFailed: ApiFailed,
)