package com.example.myapp.domain.model

import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel

data class UserModel(
    val firstName: String?=null,
    val lastName: String?=null,
    val rootItem: FileItemModel?=null
)