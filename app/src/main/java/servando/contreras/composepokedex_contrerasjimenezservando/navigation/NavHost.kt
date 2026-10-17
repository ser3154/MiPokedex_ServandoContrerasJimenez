package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import servando.contreras.composepokedex_contrerasjimenezservando.screens.MenuPokedexScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.PokemonDetailScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.PokemonHuntScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.ProfileScreen
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.PokemonViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: PokemonViewModel,
    innerPadding: PaddingValues
) {
    NavHost(navController = navController, startDestination = PokemonList) {
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onPokemonClick = { number -> navController.navigate(PokemonDetail(number)) },
                onNavigateToHunt = {
                    navController.navigate(PokemonHunt) {
                        popUpTo<PokemonList> { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
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
        composable<PokemonHunt> {
            PokemonHuntScreen(
                innerPadding = innerPadding,
                viewModel = viewModel,
                onPokemonClick = { number -> navController.navigate(PokemonDetail(number)) }
            )
        }
        composable<Profile> {
            ProfileScreen(innerPadding, capturedCount = viewModel.state.team.size)
        }
    }
}