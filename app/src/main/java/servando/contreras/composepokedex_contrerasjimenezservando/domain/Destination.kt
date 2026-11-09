package servando.contreras.composepokedex_contrerasjimenezservando.domain

import servando.contreras.composepokedex_contrerasjimenezservando.R
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.PokemonHunt
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.PokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.Profile

enum class Destination(
    val route: Any,
    val label: String,
    val icon: Int
) {
    HOME(PokemonList, "Home", R.drawable.home),
    HUNT(PokemonHunt, "New Pokemon", R.drawable.hunt),
    PROFILE(Profile, "Profile", R.drawable.profile)
}