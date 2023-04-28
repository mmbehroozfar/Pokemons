package com.mmb.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    @SerialName("abilities")
    val abilities: List<AbilityResponse>,
    @SerialName("base_experience")
    val baseExperience: Int,
    @SerialName("height")
    val height: Int,
    @SerialName("held_items")
    val heldItems: List<HeldItemResponse>,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("stats")
    val stats: List<StatResponse>,
    @SerialName("weight")
    val weight: Int,
)