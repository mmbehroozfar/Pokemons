package com.mmb.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.data.local.datasource.PokemonLocalDataSource
import com.mmb.data.mapper.PokemonDetailMapper
import com.mmb.data.mapper.PokemonMapper
import com.mmb.data.mediator.PokemonRemoteMediator
import com.mmb.data.remote.datasource.PokemonRemoteDataSource
import com.mmb.domain.extension.mapPagingData
import com.mmb.domain.model.Pokemon
import com.mmb.domain.model.PokemonDetail
import com.mmb.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl @Inject constructor(
    private val localDataSource: PokemonLocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource,
    private val pokemonRemoteMediator: PokemonRemoteMediator,
    private val pokemonMapper: PokemonMapper,
    private val pokemonDetailMapper: PokemonDetailMapper,
) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun observePagedRepository(pagingConfig: PagingConfig): Flow<PagingData<Pokemon>> =
        Pager(
            config = pagingConfig,
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = {
                localDataSource.observePokemonPagedList()
            }
        ).flow.mapPagingData(pokemonMapper::invoke)

    override suspend fun fetchPokemonDetail(name: String) {
        return pokemonDetailMapper(remoteDataSource.getPokemon(name)).let {
            localDataSource.insert(it)
        }
    }

    override fun getPokemonDetail(name: String): Flow<PokemonDetail> {
        return localDataSource.getPokemonDetail(name)
            .filterNotNull()
            .map(pokemonDetailMapper::invoke)
    }
}