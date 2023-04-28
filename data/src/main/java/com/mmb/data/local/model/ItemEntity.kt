package com.mmb.data.local.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemEntity(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)