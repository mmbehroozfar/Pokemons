package com.mmb.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val abilities: List<Ability>,
    val baseExperience: Int,
    val height: Int,
    val heldItems: List<HeldItem>,
    val order: Int,
    val stats: List<Stat>,
    val weight: Int,
)