package com.mmb.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mmb.data.local.typeconverter.AbilityConvertor
import com.mmb.data.local.typeconverter.HeldItemConvertor
import com.mmb.data.local.typeconverter.StatConvertor

@Entity(
    tableName = "pokemons_detail",
    indices = [
        Index(value = ["name"], unique = true)
    ]
)
data class PokemonDetailEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @TypeConverters(AbilityConvertor::class)
    @ColumnInfo("abilities")
    val abilities: List<AbilityEntity>,
    @ColumnInfo("base_experience")
    val baseExperience: Int,
    @ColumnInfo("height")
    val height: Int,
    @TypeConverters(HeldItemConvertor::class)
    @ColumnInfo("held_items")
    val heldItems: List<HeldItemEntity>,
    @ColumnInfo("order")
    val order: Int,
    @ColumnInfo("stats")
    @TypeConverters(StatConvertor::class)
    val stats: List<StatEntity>,
    @ColumnInfo("weight")
    val weight: Int,
)