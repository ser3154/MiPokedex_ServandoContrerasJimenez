package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.R
import servando.contreras.composepokedex_contrerasjimenezservando.components.FavoritesRow
import servando.contreras.composepokedex_contrerasjimenezservando.components.MenuPokedex
import servando.contreras.composepokedex_contrerasjimenezservando.components.PokedexGrid
import servando.contreras.composepokedex_contrerasjimenezservando.data.getFavoritePokemons
import servando.contreras.composepokedex_contrerasjimenezservando.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Blue
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Green
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.LightBlue
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.LightGreen
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel,
    onPokemonClick: (Int) -> Unit = {},
    onNavigateToHunt: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    // Se lee de SharedPreferences al abrir la pantalla
    var grid by remember { mutableStateOf(userViewModel.getPokemonDisplayGrid()) }

    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Mi Pokedex", style = MaterialTheme.typography.headlineSmall)
                OutlinedButton(onClick = {
                    userViewModel.logout()
                    onLogout()
                }) {
                    Text("Cerrar sesión")
                }
            }

            Text(
                text = "Mis Favoritos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
            FavoritesRow(getFavoritePokemons(), onPokemonClick)
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Todos mis pokemones", style = MaterialTheme.typography.titleLarge)
                Switch(
                    checked = grid,
                    onCheckedChange = {
                        grid = it
                        userViewModel.savePokemonDisplayGrid(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Green,
                        checkedTrackColor = LightGreen,
                        uncheckedThumbColor = Blue,
                        uncheckedTrackColor = LightBlue,
                        uncheckedBorderColor = Color.Transparent
                    ),
                    thumbContent = {
                        Icon(
                            painter = painterResource(if (grid) R.drawable.grid else R.drawable.list),
                            contentDescription = if (grid) "grid icon" else "list icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                )
            }
            if (grid) PokedexGrid(pokemonList, onPokemonClick) else MenuPokedex(pokemonList, onPokemonClick)
        }

        ExtendedFloatingActionButton(
            onClick = onNavigateToHunt,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Text("Nueva captura")
        }
    }
}