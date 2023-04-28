package com.mmb.data.local.datasource

import androidx.paging.PagingSource
import com.mmb.data.local.dao.PokemonDao
import com.mmb.data.local.model.PokemonDetailEntity
import com.mmb.data.local.model.PokemonEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PokemonLocalDataSourceImpl @Inject constructor(
    private val dao: PokemonDao,
) : PokemonLocalDataSource {

    override suspend fun insert(entities: List<PokemonEntity>) = dao.insert(entities)

    override suspend fun insert(entity: PokemonDetailEntity) = dao.insert(entity)

    override fun observePokemonPagedList(): PagingSource<Int, PokemonEntity> =
        dao.observePokemonPagedList()

    override fun getPokemonDetail(name: String): Flow<PokemonDetailEntity?> =
        dao.getPokemonDetail(name)

    override suspend fun clearPokemons() = dao.clearPokemons()

}