package com.mmb.ui_pokemon.detail

import com.mmb.domain.base.Result
import com.mmb.domain.model.PokemonDetail
import com.mmb.ui_common.mvi.State

data class PokemonDetailState(
    val status: Result<Unit> = Result.Loading,
    val pokemonDetail: PokemonDetail = PokemonDetail(),
) : State