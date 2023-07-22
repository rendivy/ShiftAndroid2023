package com.example.diapplication.presentation

import com.example.diapplication.domain.entity.UserGeocoding

sealed class UserState {
    object Loading : UserState()
    data class Content(val userGeocoding: UserGeocoding) : UserState()
    data class Error(val error: Boolean) : UserState()

}