package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import servando.contreras.composepokedex_contrerasjimenezservando.R

enum class Destination(
    val route: Any,
    val label: String,
    val icon: Int
) {
    HOME(PokemonList, "Home", R.drawable.home),
    HUNT(PokemonHunt, "New Pokemon", R.drawable.hunt),
    PROFILE(Profile, "Profile", R.drawable.profile)
}