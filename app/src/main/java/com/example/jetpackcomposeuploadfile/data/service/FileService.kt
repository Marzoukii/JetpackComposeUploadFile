package com.example.jetpackcomposeuploadfile.data.service

import com.example.myapp.domain.model.FileItemModel
import com.example.jetpackcomposeuploadfile.data.model.FileItemJson
import com.example.myapp.domain.model.UserJson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Streaming
import retrofit2.Response
interface FileService {

    @GET("me")
    suspend fun getCurrentUser(): Response<UserJson>

    @GET("items/{id}")
    suspend fun getFolderContent(@Path("id") folderId: String?): List<FileItemJson>

    @POST("items/{id}")
    @Headers("Content-Type: application/json")
    suspend fun createFolder(
        @Path("id") parentId: String,
        @Body body: Map<String, String>
    ): FileItemModel

    @POST("items/{id}")
    suspend fun uploadFile(
        @Path("id") parentId: String,
        @Header("Content-Disposition") contentDisposition: String,
        @Header("Content-Type") contentType: String = "application/octet-stream",
        @Body fileBody: RequestBody
    ): FileItemModel

    @DELETE("items/{id}")
    suspend fun deleteItem(@Path("id") itemId: String?): Response<Unit>

    @GET("items/{id}/data")
    @Streaming
    suspend fun downloadItem(@Path("id") itemId: String): ResponseBody
}