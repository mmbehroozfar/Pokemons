package com.mmb.ui_pokemon.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mmb.domain.base.Result
import com.mmb.domain.base.onError
import com.mmb.domain.base.onSuccess
import com.mmb.domain.usecase.FetchPokemonDetailUseCase
import com.mmb.domain.usecase.GetPokemonDetailUseCase
import com.mmb.ui_common.mvi.BaseViewModel
import com.mmb.ui_pokemon.PokemonGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchPokemonDetailUseCase: FetchPokemonDetailUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) : BaseViewModel<PokemonDetailIntent, PokemonDetailState, PokemonDetailEffect>(PokemonDetailState()) {

    private val name =
        requireNotNull(savedStateHandle.get<String>(PokemonGraph.PokemonDetail.NAME_ARGUMENT))

    init {
        dispatchIntent(PokemonDetailIntent.Init)
        dispatchIntent(PokemonDetailIntent.FetchPokemonDetail)
    }

    override suspend fun handleIntent(intent: PokemonDetailIntent) {
        when (intent) {
            is PokemonDetailIntent.Init -> handleInit()
            is PokemonDetailIntent.FetchPokemonDetail -> handleFetchPokemonDetail()
        }
    }

    private fun handleInit() {
        getPokemonDetailUseCase(name)
            .onEach {
                reduceState {
                    copy(pokemonDetail = it)
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun handleFetchPokemonDetail() {
        reduceState { copy(status = Result.Loading) }
        fetchPokemonDetailUseCase(name)
            .onSuccess {
                reduceState { copy(status = Result.Success(Unit)) }
            }
            .onError {
                reduceState { copy(status = Result.Error(it)) }
            }
    }

}