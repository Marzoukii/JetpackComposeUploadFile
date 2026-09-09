package com.example.myapp.data.mapper

import com.example.myapp.domain.model.FileItem
import com.example.myapp.domain.model.FileItemJson
import com.example.myapp.domain.model.User
import com.example.myapp.domain.model.UserJson

fun FileItemJson.toDomain(): FileItem {
    return FileItem(
        id = this.id,
        parentId = this.parentId,
        name = this.name,
        isDirectory = this.isDir,
        date = this.modificationDate,
        size = this.size,
        contentType = this.contentType
    )
}

fun List<FileItemJson>.toDomain(): List<FileItem> = map { it.toDomain() }

fun UserJson.toDomain(): User {
    return User(
        firstName = this.firstName, lastName = this.lastName, rootItem = this.rootItem.toDomain()
    )
}