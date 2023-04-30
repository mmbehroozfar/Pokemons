package com.mmb.ui_pokemon.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mmb.domain.model.Pokemon
import com.mmb.ui_common.mvi.collectAsState
import com.mmb.ui_common.mvi.collectEffect
import com.mmb.ui_pokemon.R
import com.mmb.ui_pokemon.component.ErrorItem
import com.mmb.ui_pokemon.component.LoadingItem
import com.mmb.ui_pokemon.component.PokemonItem
import kotlinx.coroutines.flow.Flow

@Composable
internal fun PokemonsListScreen(
    onNavigateToDetailScreen: (String) -> Unit,
    viewModel: PokemonsListViewModel = hiltViewModel(),
) {
    viewModel.collectEffect { effect ->
        when (effect) {
            is PokemonsListEffect.OnNavigateToDetailScreen -> onNavigateToDetailScreen(effect.name)
        }
    }

    PokemonsListScreen(viewModel = viewModel)
}

@Composable
private fun PokemonsListScreen(viewModel: PokemonsListViewModel) {
    val state by viewModel.collectAsState()

    PokemonsListScreen(
        state = state,
        onItemClicked = {
            viewModel.dispatchIntent(PokemonsListIntent.OnPokemonItemClicked(it))
        }
    )
}

@Composable
private fun PokemonsListScreen(
    state: PokemonsListState,
    onItemClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.pokemons),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

        PokemonList(
            pokemons = state.pokemons,
            onItemClicked = onItemClicked,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun PokemonList(
    pokemons: Flow<PagingData<Pokemon>>,
    onItemClicked: (String) -> Unit,
) {
    val pagingItems = pokemons.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val pagingIsError = pagingItems.loadState.mediator?.refresh is LoadState.Error ||
            pagingItems.loadState.mediator?.append is LoadState.Error
    val pagingIsLoading = pagingItems.loadState.append is LoadState.Loading
    val pagingIsRefreshing = pagingItems.loadState.refresh is LoadState.Loading

    val refreshState = rememberPullRefreshState(
        refreshing = pagingIsRefreshing,
        onRefresh = pagingItems::refresh,
    )
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.pullRefresh(refreshState),
            state = listState,
        ) {
            items(pagingItems, key = { it.id }) {
                it?.let { pokemon ->
                    PokemonItem(
                        pokemon = pokemon,
                        onItemClicked = onItemClicked,
                    )
                }
            }

            if (pagingIsLoading) {
                item {
                    LoadingItem()
                }
            } else if (pagingIsError) {
                item {
                    val errorLoadState = arrayOf(
                        pagingItems.loadState.append,
                        pagingItems.loadState.refresh
                    )
                        .filterIsInstance(LoadState.Error::class.java)
                        .firstOrNull()
                    ErrorItem(
                        error = errorLoadState?.error,
                        onRetry = pagingItems::retry,
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = pagingIsRefreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}


