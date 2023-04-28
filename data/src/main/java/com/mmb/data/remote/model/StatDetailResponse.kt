package com.mmb.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatDetailResponse(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)