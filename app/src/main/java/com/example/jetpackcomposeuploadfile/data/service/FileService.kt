package com.example.jetpackcomposeuploadfile.data.service

import com.example.myapp.domain.model.FileItemJson
import com.example.myapp.domain.model.UserJson
import retrofit2.http.GET
import retrofit2.http.Path

interface FileService {

    @GET("me")
    suspend fun getCurrentUser(): UserJson

    @GET("items/{id}")
    suspend fun getFolderContent(@Path("id") folderId: String?): List<FileItemJson>
}