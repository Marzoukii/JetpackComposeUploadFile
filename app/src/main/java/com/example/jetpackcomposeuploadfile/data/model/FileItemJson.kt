package com.example.myapp.domain.model

import com.google.gson.annotations.SerializedName

data class FileItemJson(
    @SerializedName("id")               val id: String,
    @SerializedName("parentId")         val parentId: String?,
    @SerializedName("name")             val name: String,
    @SerializedName("isDir")            val isDir: Boolean,
    @SerializedName("modificationDate") val modificationDate: String,
    @SerializedName("size")             val size: Long?,
    @SerializedName("contentType")      val contentType: String?
)