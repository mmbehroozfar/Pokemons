package com.mmb.domain.model

data class PokemonDetail(
    val id: Int = 0,
    val name: String = "",
    val abilities: List<Ability> = emptyList(),
    val baseExperience: Int = 0,
    val height: Int = 0,
    val heldItems: List<HeldItem> = emptyList(),
    val order: Int = 0,
    val stats: List<Stat> = emptyList(),
    val weight: Int = 0,
)