package com.mmb.ui_pokemon.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mmb.domain.base.isError
import com.mmb.domain.base.isLoading
import com.mmb.domain.model.PokemonDetail
import com.mmb.ui_common.mvi.collectAsState
import com.mmb.ui_pokemon.R
import com.mmb.ui_pokemon.component.ErrorItem

@Composable
internal fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    PokemonDetailScreen(
        state = state,
        onRefreshData = {
            viewModel.dispatchIntent(PokemonDetailIntent.FetchPokemonDetail)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PokemonDetailScreen(
    state: PokemonDetailState,
    onRefreshData: () -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = state.status.isLoading(),
        onRefresh = onRefreshData,
    )
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(8.dp)
                .pullRefresh(refreshState)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (state.status.isError())
                ErrorItem(onRetry = onRefreshData, error = state.status.error)

            if (state.pokemonDetail.id != 0)
                PokemonDetail(
                    pokemonDetail = state.pokemonDetail,
                )
        }

        PullRefreshIndicator(
            refreshing = state.status.isLoading(),
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun PokemonDetail(
    pokemonDetail: PokemonDetail,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/${pokemonDetail.id}.png",
            contentDescription = pokemonDetail.name,
            contentScale = ContentScale.Fit
        )

        Text(text = pokemonDetail.name)
    }

    Text(text = stringResource(id = R.string.base_experience, pokemonDetail.baseExperience))

    Text(text = stringResource(id = R.string.height, pokemonDetail.height))

    Text(text = stringResource(id = R.string.order, pokemonDetail.order))

    Text(text = stringResource(id = R.string.weight, pokemonDetail.weight))

    Text(
        text = stringResource(
            id = R.string.abilities,
            pokemonDetail.abilities.joinToString { it.ability.name })
    )

    Text(
        text = stringResource(
            id = R.string.heldItems,
            pokemonDetail.heldItems.joinToString { it.item.name })
    )
}



