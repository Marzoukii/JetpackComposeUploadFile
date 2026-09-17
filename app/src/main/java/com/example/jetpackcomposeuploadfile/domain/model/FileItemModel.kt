package com.example.myapp.domain.model

data class FileItemModel(
    val id: String?=null,
    val parentId: String?=null,
    val name: String?=null,
    val isDirectory: Boolean?=null,
    val date: String?=null,
    val size: Long?=null,
    val contentType: String?=null
)