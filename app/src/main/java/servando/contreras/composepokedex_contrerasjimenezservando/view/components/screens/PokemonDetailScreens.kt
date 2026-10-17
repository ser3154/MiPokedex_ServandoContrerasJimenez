package servando.contreras.composepokedex_contrerasjimenezservando.view.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import servando.contreras.composepokedex_contrerasjimenezservando.model.data.getPokemonByNumber

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemon: Int){
    val pokemon = getPokemonByNumber(pokemon)
    Column(Modifier.padding(innerPadding)) {

        Text(pokemon.name)
        Image(painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image")
    }
}

