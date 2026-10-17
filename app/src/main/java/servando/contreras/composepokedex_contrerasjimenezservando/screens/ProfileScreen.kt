package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.data.getPokemonByNumber

@Composable
fun ProfileScreen(innerPadding: PaddingValues, capturedCount: Int = 0) {
    val companion = getPokemonByNumber(94) // Gengar

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Servando Contreras")
        Text("Compañero: ${companion.name}")
        Image(
            painter = painterResource(companion.image),
            contentDescription = "${companion.name} image",
            modifier = Modifier.size(120.dp)
        )
        Text("Pokemones capturados: $capturedCount")
    }
}