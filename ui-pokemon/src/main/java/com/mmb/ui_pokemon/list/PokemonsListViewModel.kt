package com.mmb.ui_pokemon.list

import com.mmb.domain.usecase.ObservePagedPokemonUseCase
import com.mmb.ui_common.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonsListViewModel @Inject constructor(
    private val observePagedPokemonUseCase: ObservePagedPokemonUseCase,
) : BaseViewModel<PokemonsListIntent, PokemonsListState, PokemonsListEffect>(PokemonsListState()) {

    init {
        dispatchIntent(PokemonsListIntent.Init)
    }

    override suspend fun handleIntent(intent: PokemonsListIntent) {
        when (intent) {
            PokemonsListIntent.Init -> handleInit()
            is PokemonsListIntent.OnPokemonItemClicked -> handleOnPokemonItemClicked(intent.name)
        }
    }

    private fun handleInit() {
        observePagedPokemonUseCase(Unit)
        reduceState {
            copy(pokemons = observePagedPokemonUseCase.flow)
        }
    }

    private fun handleOnPokemonItemClicked(name: String) {
        dispatchEffect(PokemonsListEffect.OnNavigateToDetailScreen(name))
    }

}