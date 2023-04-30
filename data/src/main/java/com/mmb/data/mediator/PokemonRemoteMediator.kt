package com.mmb.data.mediator

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mmb.data.local.datasource.PokemonLocalDataSource
import com.mmb.data.local.datasource.PreferenceLocalDataSource
import com.mmb.data.local.model.PokemonEntity
import com.mmb.data.mapper.PokemonMapper
import com.mmb.data.remote.datasource.PokemonRemoteDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val preferenceLocalDataSource: PreferenceLocalDataSource,
    private val pokemonMapper: PokemonMapper,
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>,
    ): MediatorResult {
        try {
            if (loadType == LoadType.REFRESH) {
                pokemonLocalDataSource.clearPokemons()
                preferenceLocalDataSource.setPagingOffset(0)
            } else if (loadType == LoadType.PREPEND) {
                return MediatorResult.Success(false)
            }
            val offset = preferenceLocalDataSource.getPagingOffset().first()
            val response = pokemonRemoteDataSource.getPagedPokemons(offset)

            val endOfPaginationReached = if (response.next == null) {
                preferenceLocalDataSource.setPagingOffset(
                    Integer.MAX_VALUE
                )
                true
            } else {
                Uri.parse(response.next).getQueryParameter("offset")?.toInt()?.let {
                    preferenceLocalDataSource.setPagingOffset(it)
                }
                false
            }

            response.pokemonResponses.map {
                pokemonMapper(it)
            }.let {
                pokemonLocalDataSource.insert(it)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

}