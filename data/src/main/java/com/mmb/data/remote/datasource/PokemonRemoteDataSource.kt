package com.mmb.data.remote.datasource

import com.mmb.data.remote.model.PokemonDataResponse
import com.mmb.data.remote.model.PokemonDetailResponse

interface PokemonRemoteDataSource {

    suspend fun getPagedPokemons(offset: Int): PokemonDataResponse

    suspend fun getPokemon(name: String): PokemonDetailResponse
}