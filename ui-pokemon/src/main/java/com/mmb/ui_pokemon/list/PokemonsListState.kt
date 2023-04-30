package com.mmb.ui_pokemon.list

import androidx.paging.PagingData
import com.mmb.domain.model.Pokemon
import com.mmb.ui_common.mvi.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PokemonsListState(
    val isLoading: Boolean = false,
    val pokemons: Flow<PagingData<Pokemon>> = emptyFlow(),
) : State