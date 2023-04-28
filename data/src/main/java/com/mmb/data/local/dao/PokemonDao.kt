package com.mmb.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mmb.data.local.model.PokemonDetailEntity
import com.mmb.data.local.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<PokemonEntity>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PokemonDetailEntity)

    @Query("SELECT * FROM pokemons")
    fun observePokemonPagedList(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemons_detail WHERE name = :name")
    fun getPokemonDetail(name: String): Flow<PokemonDetailEntity?>

    @Query("DELETE FROM pokemons")
    suspend fun clearPokemons()
}