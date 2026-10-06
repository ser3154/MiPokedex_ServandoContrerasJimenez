package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.components.FavoritesRow
import servando.contreras.composepokedex_contrerasjimenezservando.components.PokedexGrid
import servando.contreras.composepokedex_contrerasjimenezservando.data.pokemonList

@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )
        FavoritesRow(pokemonList.filter { it.favorite })
        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 10.dp)
        )
        PokedexGrid(pokemonList)
    }
}