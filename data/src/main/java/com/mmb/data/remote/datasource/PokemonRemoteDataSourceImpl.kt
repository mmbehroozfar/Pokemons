package com.mmb.data.remote.datasource

import com.mmb.data.remote.api.PokemonApiService
import com.mmb.data.remote.util.bodyOrThrow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val apiService: PokemonApiService,
) : PokemonRemoteDataSource {

    override suspend fun getPagedPokemons(offset: Int) = bodyOrThrow {
        apiService.getPagedPokemons(offset = offset)
    }

    override suspend fun getPokemon(name: String) = bodyOrThrow {
        apiService.getPokemon(name)
    }
}