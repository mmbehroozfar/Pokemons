package com.mmb.data.remote.api

import com.mmb.data.remote.model.PokemonDataResponse
import com.mmb.data.remote.model.PokemonDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("v2/pokemon")
    suspend fun getPagedPokemons(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int,
    ): Response<PokemonDataResponse>

    @GET("v2/pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Response<PokemonDetailResponse>

}