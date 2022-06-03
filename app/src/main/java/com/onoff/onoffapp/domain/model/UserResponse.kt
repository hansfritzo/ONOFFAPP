package com.onoff.onoffapp.domain.model

import com.onoff.onoffapp.data.model.ApiFailed

/**
 * Created by hans fritz ortega on 20/06/02.
 */
data class UserResponse(
    var user: List<User>,
    var apiFailed: ApiFailed,
)