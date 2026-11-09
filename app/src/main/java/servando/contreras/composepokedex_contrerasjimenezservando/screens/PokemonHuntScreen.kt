package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.components.PokemonRow
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Green
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.PokemonViewModel

@Composable
fun PokemonHuntScreen(
    innerPadding: PaddingValues,
    viewModel: PokemonViewModel,
    onPokemonClick: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.searchPokemon() }) {
            Text("Buscar pokemon en la hierba")
        }

        Spacer(Modifier.height(18.dp))

        viewModel.wildPokemon?.let { pokemon ->
            Text("¡Apareció un ${pokemon.name} salvaje!")
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = pokemon.name,
                modifier = Modifier
                    .height(90.dp)
                    .padding(vertical = 15.dp)
            )
            Button(onClick = { viewModel.capturePokemon() }) {
                Text("¡Capturar a ${pokemon.name}!")
            }
        }

        if (viewModel.gonePokemon) {
            Text("El pokemon se escapó", color = MaterialTheme.colorScheme.error)
        }
        if (viewModel.captured) {
            Text("¡${viewModel.state.lastCaptured?.name} capturado!", color = Green)
        }

        Spacer(Modifier.height(20.dp))

        Text("Pokemones capturados", style = MaterialTheme.typography.titleMedium)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.state.team) { pokemon ->
                PokemonRow(pokemon, onClick = { onPokemonClick(pokemon.number) })
            }
        }
    }
}