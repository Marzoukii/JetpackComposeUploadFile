package com.example.jetpackcomposeuploadfile.data.model

import com.google.gson.annotations.SerializedName


data class FileItemJson(
    @SerializedName("id")               val id: String?=null,
    @SerializedName("parentId")         val parentId: String?=null,
    @SerializedName("name")             val name: String?=null,
    @SerializedName("isDir")            val isDir: Boolean?=null,
    @SerializedName("modificationDate") val modificationDate: String?=null,
    @SerializedName("size")             val size: Long?=null,
    @SerializedName("contentType")      val contentType: String?=null
)