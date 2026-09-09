package com.example.jetpackcomposeuploadfile.domain.enums

import com.example.myapp.domain.model.UserJson

sealed class UserState {
    object Loading : UserState()
    data class Success(val userJson: UserJson) : UserState()
    data class Error(val message: String) : UserState()
}
