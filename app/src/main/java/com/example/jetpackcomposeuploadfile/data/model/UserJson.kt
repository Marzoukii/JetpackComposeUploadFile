package com.example.myapp.domain.model

import com.google.gson.annotations.SerializedName

data class UserJson(
    @SerializedName("firstName") val firstName: String?=null,
    @SerializedName("lastName")  val lastName: String?=null,
    @SerializedName("rootItem")  val rootItem: FileItemJson?=null
)