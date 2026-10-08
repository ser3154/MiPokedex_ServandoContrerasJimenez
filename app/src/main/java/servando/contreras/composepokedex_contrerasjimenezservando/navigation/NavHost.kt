package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import servando.contreras.composepokedex_contrerasjimenezservando.screens.MenuPokedexScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.PokemonDetailScreen

@Composable
fun AppNavHost(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PokemonList) {
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onPokemonClick = { number -> navController.navigate(PokemonDetail(number)) }
            )
        }
        composable<PokemonDetail> {
            val pokemon = it.arguments?.getInt("pokemon") ?: -1
            PokemonDetailScreen(
                pokemonNumber = pokemon,
                innerPadding = innerPadding,
                onNavigateToPokemon = { number ->
                    navController.navigate(PokemonDetail(number)) {
                        popUpTo<PokemonList>()
                    }
                }
            )
        }
    }
}