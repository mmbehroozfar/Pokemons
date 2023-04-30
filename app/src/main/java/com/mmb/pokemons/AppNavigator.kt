package com.mmb.pokemons

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.mmb.ui_pokemon.PokemonGraph
import com.mmb.ui_pokemon.pokemonGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigator(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = PokemonGraph.route,
    ) {
        pokemonGraph(navController)
    }
}