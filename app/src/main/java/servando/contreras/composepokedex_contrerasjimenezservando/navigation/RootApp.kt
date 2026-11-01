package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.PokemonViewModel
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

@Composable
fun RootApp(userViewModel: UserViewModel) {
    val navController = rememberNavController()
    val viewModel: PokemonViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val showBottomBar = currentDestination?.hasRoute(Login::class) != true

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    Destination.entries.forEach { destination ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any {
                                it.hasRoute(destination.route::class)
                            } == true,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo<PokemonList> { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(destination.icon),
                                    contentDescription = destination.label
                                )
                            },
                            label = { Text(destination.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(navController, viewModel, userViewModel, innerPadding)
    }
}