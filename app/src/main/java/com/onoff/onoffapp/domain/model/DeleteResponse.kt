package com.onoff.onoffapp.domain.model

import com.onoff.onoffapp.data.model.ApiFailed

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class DeleteResponse(
    val msg: String,
    val apiFailed: ApiFailed
    )



