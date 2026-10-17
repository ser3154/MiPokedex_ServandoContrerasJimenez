package servando.contreras.composepokedex_contrerasjimenezservando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import servando.contreras.composepokedex_contrerasjimenezservando.compose.Ability
import servando.contreras.composepokedex_contrerasjimenezservando.compose.Chip
import servando.contreras.composepokedex_contrerasjimenezservando.compose.PokemonNavButton
import servando.contreras.composepokedex_contrerasjimenezservando.model.domain.Pokemon
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.MyApp
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.ComposePokedex_ContrerasJimenezServandoTheme
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.ElectricYellow
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.White

val pokemon = Pokemon(
    name = "Pikachu",
    number = 25,
    type = "Electrico",
    description = "SDASDASDASD.",
    height = 0.4f,
    weight = 6.0f,
    favorite = true,
    ability = "Estatica",
    image = R.drawable.pikachu
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePokedex_ContrerasJimenezServandoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(innerPadding)
                }
            }
        }
    }
}

@Composable
fun PokemonHeader(name: String, number: Int, fav: Boolean){
    Row(Modifier.fillMaxWidth().padding(15.dp), horizontalArrangement = Arrangement.SpaceBetween){
        Column() {
            Text(name, )
            Text("#${number}", modifier = Modifier.align(Alignment.End))
        }
        Box{
            Image(painter = painterResource(R.drawable.pokeball),
                contentDescription = "pokeball image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp).offset(30.dp, 20.dp)
            )
            Image(painter = painterResource(if (fav)R.drawable.star_filled else R.drawable.star_outline),
                contentDescription = if (fav)"yellow star filled" else "yellow star out",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun PokemonCard(name: String, weight: Float, height: Float, description: String, ability: String,Type: String, image:Int){
    Box (contentAlignment = Alignment.TopCenter){
        Image(painter = painterResource(image), contentDescription = "Pikachu", Modifier.offset(0.dp,-80.dp).zIndex(2f).size(130.dp), contentScale = ContentScale.Fit)
        Card (Modifier.fillMaxWidth().fillMaxHeight(), elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), colors = CardDefaults.cardColors(containerColor = White)){
            Column (Modifier.fillMaxWidth()) {
                Chip(Type, ElectricYellow, Modifier.padding(top = 70.dp).align(Alignment.CenterHorizontally))

                Row (modifier = Modifier.fillMaxWidth(.8f).align(Alignment.CenterHorizontally).padding(top = 15.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                    Column() {
                        Ability("row", label = "Altura", value = "${pokemon.height} m")
                        Ability("row", label= "Peso", value = "${pokemon.weight} kg")
                    }
                    Ability("column", label = "Habilidad", value = ability)
                }

                Row (Modifier.fillMaxWidth(.8f).align(Alignment.CenterHorizontally).padding(25.dp),){
                    Text(description, textAlign = TextAlign.Center)
                }
                Spacer(Modifier.weight(1f))
                Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = .8.dp), horizontalArrangement = Arrangement.SpaceBetween){
                    PokemonNavButton(position = "left",image = R.drawable.arbok, name = "Arbok", number = 24)
                    PokemonNavButton(position = "right", image = R.drawable.raichu, name = "Raichu", number = 26)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHeaderPreview(){
    ComposePokedex_ContrerasJimenezServandoTheme() {
        PokemonHeader("Pikachu", 25, true)
    }
}

@Composable
fun Greeting(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(Modifier.background(ElectricYellow, RectangleShape)){
        PokemonHeader(pokemon.name, pokemon.number, pokemon.favorite)
        PokemonCard(pokemon.name,pokemon.weight,pokemon.height, pokemon.description, pokemon.ability,pokemon.type, pokemon.image)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePokedex_ContrerasJimenezServandoTheme {
        Greeting(
            pokemon = Pokemon(
                name = "Pikachu",
                number = 25,
                type = "Electrico",
                description = "Cuando se enfada, este Pokémon descarga la energía que almacena en el interior de las bolsas de las mejillas.",
                height = 0.4f,
                weight = 6.0f,
                favorite = true,
                ability = "Estatica",
                image = R.drawable.pikachu
            )
        )
    }
}