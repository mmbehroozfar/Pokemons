package com.mmb.data.mapper

import com.mmb.data.local.model.PokemonDetailEntity
import com.mmb.data.remote.model.PokemonDetailResponse
import com.mmb.domain.model.PokemonDetail
import javax.inject.Inject

class PokemonDetailMapper @Inject constructor(
    private val abilityMapper: AbilityMapper,
    private val heldItemMapper: HeldItemMapper,
    private val statMapper: StatMapper,
) {

    operator fun invoke(response: PokemonDetailResponse): PokemonDetailEntity {
        return PokemonDetailEntity(
            id = response.id,
            name = response.name,
            abilities = response.abilities.map { abilityMapper(it) },
            baseExperience = response.baseExperience,
            height = response.height,
            heldItems = response.heldItems.map { heldItemMapper(it) },
            order = response.order,
            stats = response.stats.map { statMapper(it) },
            weight = response.weight,
        )
    }

    operator fun invoke(entity: PokemonDetailEntity): PokemonDetail {
        return PokemonDetail(
            id = entity.id,
            name = entity.name,
            abilities = entity.abilities.map { abilityMapper(it) },
            baseExperience = entity.baseExperience,
            height = entity.height,
            heldItems = entity.heldItems.map { heldItemMapper(it) },
            order = entity.order,
            stats = entity.stats.map { statMapper(it) },
            weight = entity.weight,
        )
    }
}