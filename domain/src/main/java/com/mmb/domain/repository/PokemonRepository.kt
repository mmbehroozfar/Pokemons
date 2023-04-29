package com.mmb.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.domain.model.Pokemon
import com.mmb.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun observePagedRepository(pagingConfig: PagingConfig): Flow<PagingData<Pokemon>>

    suspend fun fetchPokemonDetail(name: String)

    fun getPokemonDetail(name: String): Flow<PokemonDetail>

}