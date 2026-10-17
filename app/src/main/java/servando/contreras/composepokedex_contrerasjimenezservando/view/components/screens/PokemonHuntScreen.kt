package servando.contreras.composepokedex_contrerasjimenezservando.view.components.screens

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Typography
import servando.contreras.composepokedex_contrerasjimenezservando.view.components.PokemonRow
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.PokemonViewModel

@Composable
fun PokemonHuntScreen (innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel()){

    Column(Modifier.fillMaxWidth().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {viewModel.searchPokemon()}){
            Text("Buscar pokemon en la hierva")
        }

        Spacer(Modifier.size(18.dp))

        viewModel.wildPokemon?.let {
          pokemon -> Text("Apacecio un ${pokemon.name} salvaje")
            Image(painterResource(pokemon.image),
                modifier = Modifier.height(90.dp).padding(horizontal = 0.dp, vertical = 15.dp)
                , contentDescription = "pokemon")
            Spacer(Modifier.size(20.dp))

            Button(onClick = {viewModel.capturePokemon()}){
                Text("Capturar a ${pokemon.name}!")
            }

            if (viewModel.gonePokemon){
                Text("El pokemon se escapó ", color = Color.Magenta)
            }


            if (viewModel.captured){
                Text("Pokemon capturado!", color = Color.Green)
            }

            Spacer(Modifier.size(20.dp))

            Text("Pokemones Capturados", style = Typography.labelLarge, color = Color.Cyan)

            LazyColumn() {
                items(viewModel.capturedPokemon){
                    pokemon ->
                    PokemonRow(pokemon)
                }
            }
         }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPokemonHuntScreen(){
    PokemonHuntScreen(PaddingValues(15.dp))
}