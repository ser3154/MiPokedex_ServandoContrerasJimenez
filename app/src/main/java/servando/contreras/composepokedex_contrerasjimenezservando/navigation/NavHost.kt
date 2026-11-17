package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import servando.contreras.composepokedex_contrerasjimenezservando.screens.LoginScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.MenuPokedexScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.PokemonDetailScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.PokemonHuntScreen
import servando.contreras.composepokedex_contrerasjimenezservando.screens.ProfileScreen
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.PokemonViewModel
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite.DatabaseHelper
import servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite.PokemonDAO
import servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite.PokemonSQLRepository
import servando.contreras.composepokedex_contrerasjimenezservando.screens.AddPokemonScreen
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.AddPokemonViewModel
@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: PokemonViewModel,
    innerPadding: PaddingValues,
    userViewModel: UserViewModel
) {
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(innerPadding, onNavigateToHome = { navController.navigate(PokemonList) }, userViewModel)
        }

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
                },
                onNavigateToProfile = { navController.navigate(Profile) },
                onNavigateToAdd = { navController.navigate(AddPokemon) }
            )
        }

        composable<PokemonDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(
                pokemonNumber = route.pokemon,
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
            ProfileScreen(innerPadding, userViewModel)
        }

        composable<AddPokemon> {
            val appContext = LocalContext.current.applicationContext
            val addPokemonViewModel: AddPokemonViewModel = viewModel(
                factory = viewModelFactory {
                    initializer {
                        AddPokemonViewModel(PokemonSQLRepository(PokemonDAO(DatabaseHelper(appContext))))
                    }
                }
            )
            AddPokemonScreen(innerPadding, addPokemonViewModel)
        }
    }
}