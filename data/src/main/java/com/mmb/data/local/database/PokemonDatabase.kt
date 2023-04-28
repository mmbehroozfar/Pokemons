package com.mmb.data.local.database

import com.mmb.data.local.dao.PokemonDao

interface PokemonDatabase {

    fun pokemonDao(): PokemonDao

}