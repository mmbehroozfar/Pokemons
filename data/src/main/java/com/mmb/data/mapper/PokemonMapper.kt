package com.mmb.data.mapper

import com.mmb.data.local.model.PokemonEntity
import com.mmb.data.remote.model.PokemonResponse
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    operator fun invoke(response: PokemonResponse): PokemonEntity {
        return PokemonEntity(
            name = response.name,
            url = response.url,
        )
    }
}