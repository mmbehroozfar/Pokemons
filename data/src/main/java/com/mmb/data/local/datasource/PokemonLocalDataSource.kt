package com.mmb.data.local.datasource

import androidx.paging.PagingSource
import com.mmb.data.local.model.PokemonDetailEntity
import com.mmb.data.local.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {

    suspend fun insert(entities: List<PokemonEntity>)

    suspend fun insert(entity: PokemonDetailEntity)

    fun observePokemonPagedList(): PagingSource<Int, PokemonEntity>

    fun getPokemonDetail(name: String): Flow<PokemonDetailEntity?>

    suspend fun clearPokemons()

}