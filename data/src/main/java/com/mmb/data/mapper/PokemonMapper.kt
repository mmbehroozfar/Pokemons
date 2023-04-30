package com.mmb.data.mapper

import com.mmb.data.local.model.PokemonEntity
import com.mmb.data.remote.model.PokemonResponse
import com.mmb.domain.model.Pokemon
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    operator fun invoke(response: PokemonResponse): PokemonEntity {
        return PokemonEntity(
            id = response.url.substringBeforeLast("/").substringAfterLast("/").toInt(),
            name = response.name,
            url = response.url,
        )
    }

    operator fun invoke(entity: PokemonEntity): Pokemon {
        return Pokemon(
            id = entity.id,
            name = entity.name,
            url = entity.url,
        )
    }
}