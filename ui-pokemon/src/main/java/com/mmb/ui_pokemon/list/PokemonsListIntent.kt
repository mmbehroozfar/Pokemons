package com.mmb.ui_pokemon.list

import com.mmb.ui_common.mvi.Intent

sealed interface PokemonsListIntent : Intent {
    object Init : PokemonsListIntent
    data class OnPokemonItemClicked(val name: String) : PokemonsListIntent
}