package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import servando.contreras.composepokedex_contrerasjimenezservando.R
import servando.contreras.composepokedex_contrerasjimenezservando.compose.Ability
import servando.contreras.composepokedex_contrerasjimenezservando.compose.Chip
import servando.contreras.composepokedex_contrerasjimenezservando.compose.PokemonNavButton
import servando.contreras.composepokedex_contrerasjimenezservando.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.ComposePokedex_ContrerasJimenezServandoTheme
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.White
import servando.contreras.composepokedex_contrerasjimenezservando.utilities.getColorByType

@Composable
fun PokemonDetailScreen(
    pokemonNumber: Int,
    innerPadding: PaddingValues = PaddingValues(),
    onNavigateToPokemon: (Int) -> Unit = {}
) {
    val index = pokemonList.indexOfFirst { it.number == pokemonNumber }
    val pokemon = pokemonList.getOrNull(index)

    if (pokemon == null) {
        Box(Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
            Text("Pokémon no encontrado")
        }
        return
    }

    val colors = getColorByType(pokemon.type)

    Column(
        Modifier
            .fillMaxSize()
            .background(colors.first, RectangleShape)
            .padding(innerPadding)
    ) {
        PokemonHeader(pokemon.name, pokemon.number, pokemon.favorite, colors.second)
        PokemonCard(
            pokemon = pokemon,
            typeColors = colors,
            previous = pokemonList.getOrNull(index - 1),
            next = pokemonList.getOrNull(index + 1),
            onNavigateToPokemon = onNavigateToPokemon
        )
    }
}

@Composable
fun PokemonHeader(name: String, number: Int, fav: Boolean, textColor: Color = Color.Unspecified) {
    Row(Modifier.fillMaxWidth().padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(name, color = textColor)
            Text("#$number", color = textColor, modifier = Modifier.align(Alignment.End))
        }
        Box {
            Image(
                painter = painterResource(R.drawable.pokeball),
                contentDescription = "pokeball image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp).offset(30.dp, 20.dp)
            )
            Image(
                painter = painterResource(if (fav) R.drawable.star_filled else R.drawable.star_outline),
                contentDescription = if (fav) "yellow star filled" else "yellow star out",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    typeColors: Pair<Color, Color>,
    previous: Pokemon?,
    next: Pokemon?,
    onNavigateToPokemon: (Int) -> Unit
) {
    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = pokemon.name,
            modifier = Modifier.offset(0.dp, (-80).dp).zIndex(2f).size(130.dp),
            contentScale = ContentScale.Fit
        )
        Card(
            Modifier.fillMaxWidth().fillMaxHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            Column(Modifier.fillMaxWidth()) {
                Chip(
                    pokemon.type,
                    typeColors.first,
                    Modifier.padding(top = 70.dp).align(Alignment.CenterHorizontally),
                    textColor = typeColors.second
                )

                Row(
                    modifier = Modifier.fillMaxWidth(.8f).align(Alignment.CenterHorizontally).padding(top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column {
                        Ability("row", label = "Altura", value = "${pokemon.height} m")
                        Ability("row", label = "Peso", value = "${pokemon.weight} kg")
                    }
                    Ability("column", label = "Habilidad", value = pokemon.ability)
                }

                Row(Modifier.fillMaxWidth(.8f).align(Alignment.CenterHorizontally).padding(25.dp)) {
                    Text(pokemon.description, textAlign = TextAlign.Center)
                }
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = .8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (previous != null) {
                        PokemonNavButton(
                            position = "left",
                            image = previous.image,
                            name = previous.name,
                            number = previous.number,
                            onClick = { onNavigateToPokemon(previous.number) }
                        )
                    } else {
                        Spacer(Modifier)
                    }
                    if (next != null) {
                        PokemonNavButton(
                            position = "right",
                            image = next.image,
                            name = next.name,
                            number = next.number,
                            onClick = { onNavigateToPokemon(next.number) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    ComposePokedex_ContrerasJimenezServandoTheme {
        PokemonDetailScreen(pokemonNumber = 25)
    }
}