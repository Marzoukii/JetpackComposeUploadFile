package com.example.jetpackcomposeuploadfile.domain.mapper

import com.example.myapp.domain.model.FileItemModel
import com.example.jetpackcomposeuploadfile.data.model.FileItemJson
import javax.inject.Inject

class FileMapper @Inject constructor() {
    fun toFileItemsModel(json: FileItemJson?) =
        FileItemModel(
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

}