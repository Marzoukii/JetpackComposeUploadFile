package com.example.myapp.domain.model

data class User(
    val firstName: String?=null,
    val lastName: String?=null,
    val rootItem: FileItem?=null
)