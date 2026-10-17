package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues,
    onPokemonClick: (Int) -> Unit = {},
    onNavigateToHunt: () -> Unit = {}
) {
    var grid by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(innerPadding)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mi Pokedex", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = onNavigateToHunt) {
                Text("Nueva captura")
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
                onCheckedChange = { grid = it },
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
}