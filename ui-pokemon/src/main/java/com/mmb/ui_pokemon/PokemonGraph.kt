package com.mmb.ui_pokemon

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import com.mmb.ui_pokemon.PokemonGraph.PokemonDetail.NAME_ARGUMENT
import com.mmb.ui_pokemon.detail.PokemonDetailScreen
import com.mmb.ui_pokemon.list.PokemonsListScreen

object PokemonGraph {
    const val route = "pokemon-graph"
    const val startDestination = PokemonsList.route

    object PokemonsList {
        const val route = "list"
    }

    object PokemonDetail {
        const val route = "detail/{name}"
        const val NAME_ARGUMENT = "name"

        fun createRoute(name: String) = "detail/$name"
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.pokemonGraph(navController: NavHostController) {
    navigation(
        route = PokemonGraph.route,
        startDestination = PokemonGraph.startDestination,
    ) {
        composable(PokemonGraph.PokemonsList.route) {
            PokemonsListScreen(
                onNavigateToDetailScreen = {
                    navController.navigate(PokemonGraph.PokemonDetail.createRoute(it))
                },
            )
        }

        composable(
            route = PokemonGraph.PokemonDetail.route,
            arguments = listOf(
                navArgument(NAME_ARGUMENT) { type = NavType.StringType },
            ),
            enterTransition = {
                fadeIn()
            },
            exitTransition = {
                fadeOut()
            },
        ) {
            PokemonDetailScreen()
        }
    }
}