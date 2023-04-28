package com.mmb.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeldItemResponse(
    @SerialName("item")
    val item: ItemResponse,
)