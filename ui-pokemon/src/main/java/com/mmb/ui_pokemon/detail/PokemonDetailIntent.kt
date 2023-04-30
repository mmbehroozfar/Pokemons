package com.mmb.ui_pokemon.detail

import com.mmb.ui_common.mvi.Intent

sealed interface PokemonDetailIntent : Intent {
    object Init : PokemonDetailIntent
    object FetchPokemonDetail : PokemonDetailIntent
}