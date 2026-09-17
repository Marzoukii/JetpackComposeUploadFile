package com.example.myapp.domain.model

data class UserModel(
    val firstName: String?=null,
    val lastName: String?=null,
    val rootItem: FileItemModel?=null
)