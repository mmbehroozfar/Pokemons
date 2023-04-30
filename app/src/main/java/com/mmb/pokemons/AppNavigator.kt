package com.mmb.pokemons

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mmb.ui_pokemon.PokemonGraph
import com.mmb.ui_pokemon.pokemonGraph

@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PokemonGraph.route,
    ) {
        pokemonGraph(navController)
    }
}