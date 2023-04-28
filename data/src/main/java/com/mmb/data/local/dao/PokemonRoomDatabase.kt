package com.mmb.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mmb.data.local.database.PokemonDatabase
import com.mmb.data.local.model.PokemonDetailEntity
import com.mmb.data.local.model.PokemonEntity
import com.mmb.data.local.typeconverter.AbilityConvertor
import com.mmb.data.local.typeconverter.HeldItemConvertor
import com.mmb.data.local.typeconverter.StatConvertor

@TypeConverters(
    AbilityConvertor::class,
    HeldItemConvertor::class,
    StatConvertor::class,
)
@Database(
    entities = [
        PokemonEntity::class,
        PokemonDetailEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class PokemonRoomDatabase : RoomDatabase(), PokemonDatabase