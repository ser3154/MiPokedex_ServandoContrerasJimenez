package servando.contreras.composepokedex_contrerasjimenezservando.view.components.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.view.components.FavoritesRow
import servando.contreras.composepokedex_contrerasjimenezservando.view.components.PokedexGrid
import servando.contreras.composepokedex_contrerasjimenezservando.model.data.getFavoritePokemons
import servando.contreras.composepokedex_contrerasjimenezservando.model.data.pokemonList

@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues, onNavigateToDetail: (pokemon: Int) -> Unit, onNavigateToHunt: () -> Unit) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Favorite Pokemonms")
            Button(onClick = {onNavigateToHunt}) {
                Text("New capture")
            }
        }

        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )
        FavoritesRow(getFavoritePokemons())
        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 10.dp)
        )
        PokedexGrid(pokemonList)
    }
}