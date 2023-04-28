package com.mmb.data.local.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeldItemEntity(
    @SerialName("item")
    val item: ItemEntity,
)