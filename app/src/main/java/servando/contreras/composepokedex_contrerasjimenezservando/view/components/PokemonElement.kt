package servando.contreras.composepokedex_contrerasjimenezservando.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import servando.contreras.composepokedex_contrerasjimenezservando.model.data.bulbasaur
import servando.contreras.composepokedex_contrerasjimenezservando.model.domain.Pokemon
import servando.contreras.composepokedex_contrerasjimenezservando.utilities.getColorByType
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Brush
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.OffWhite

@Composable
fun PokemonRow(pokemon: Pokemon) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = pokemon.image),
            contentDescription = "${pokemon.name.lowercase()} image",
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = pokemon.description,
                fontSize = 10.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Height: ${pokemon.height}",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "Weight: ${pokemon.weight}",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        NumberChip(
            text = pokemon.number.toString(),
            modifier = Modifier.align(Alignment.Top),
            colors = getColorByType(pokemon.type)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonRowPreview() {
    PokemonRow(bulbasaur)
}

@Composable
fun FavoritePokemon(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)

    Column(
        modifier = Modifier.padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Box(
                modifier = Modifier.border(
                    BorderStroke(
                        5.dp,
                        Brush.sweepGradient(
                            listOf(colors.first, OffWhite, colors.first, OffWhite, colors.first)
                        )
                    )
                )
            ) {
                Image(
                    painter = painterResource(pokemon.image),
                    contentDescription = "${pokemon.name.lowercase()} image",
                    modifier = Modifier
                        .padding(5.dp)
                        .width(75.dp)
                )
            }
            NumberChip(
                text = pokemon.number.toString(),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(10.dp, 10.dp),
                colors = colors
            )
        }
        Text(text = pokemon.name, style = MaterialTheme.typography.labelLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonElementPreview() {
    FavoritePokemon(bulbasaur)
}

@Composable
fun PokemonCell(pokemon: Pokemon) {
    val colors = getColorByType(pokemon.type)
    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name.lowercase()} image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
            )
            Text(text = pokemon.name, style = MaterialTheme.typography.labelLarge)
        }
        NumberChip(
            text = pokemon.number.toString(),
            modifier = Modifier.align(Alignment.TopEnd),
            colors = colors
        )
    }
}