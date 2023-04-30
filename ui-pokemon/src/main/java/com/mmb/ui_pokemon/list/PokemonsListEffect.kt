package com.mmb.ui_pokemon.list

import com.mmb.ui_common.mvi.Effect

sealed interface PokemonsListEffect : Effect {
    data class OnNavigateToDetailScreen(val name: String) : PokemonsListEffect
}