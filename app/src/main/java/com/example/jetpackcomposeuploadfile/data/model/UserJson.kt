package com.example.myapp.domain.model

import com.google.gson.annotations.SerializedName

data class UserJson(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName")  val lastName: String,
    @SerializedName("rootItem")  val rootItem: FileItemJson
)