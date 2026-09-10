package com.example.myapp.data.mapper

import com.example.myapp.domain.model.FileItem
import com.example.myapp.domain.model.FileItemJson
import com.example.myapp.domain.model.User
import com.example.myapp.domain.model.UserJson
import javax.inject.Inject

class FileMapper @Inject constructor() {
    fun toFileItemsModel(json: FileItemJson?) =
        FileItem(
            id = json?.id,
            parentId = json?.parentId,
            name = json?.name,
            isDirectory = json?.isDir,
            date = json?.modificationDate,
            size = json?.size,
            contentType = json?.contentType
        )
    fun toFileItemsModel(json: List<FileItemJson>) =
        json.map { toFileItemsModel(it) }

    fun toUsersModel(json: UserJson?) =
        User(
            firstName = json?.firstName,
            lastName = json?.lastName,
            rootItem = toFileItemsModel(json?.rootItem)
        )
}