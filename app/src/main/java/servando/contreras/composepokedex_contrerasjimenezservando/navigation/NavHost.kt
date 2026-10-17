package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import servando.contreras.composepokedex_contrerasjimenezservando.model.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.view.components.screens.MenuPokedexScreen
import servando.contreras.composepokedex_contrerasjimenezservando.view.components.screens.PokemonDetailScreen
import servando.contreras.composepokedex_contrerasjimenezservando.view.components.screens.PokemonHuntScreen
import servando.contreras.composepokedex_contrerasjimenezservando.view.components.screens.ProfileScreen

@Composable
fun MyApp(innerPadding: PaddingValues){
    val navController = rememberNavController()

    NavHost(navController, startDestination = pokemonList){
        composable <PokemonList>{
            MenuPokedexScreen(innerPadding,{pokemon -> navController.navigate(PokemonDetail(pokemon))}, onNavigateToHunt = {navController.navigate(
                PokemonHunt)})
        }
        composable <PokemonDetail>{
            val pokemon = it.arguments?.getInt("pokemon") ?: -1
            PokemonDetailScreen(innerPadding, pokemon)
        }

        composable<PokemonHunt>{
            PokemonHuntScreen(innerPadding)
        }

        composable <Profile>{
            ProfileScreen(innerPadding)
        }
    }
}