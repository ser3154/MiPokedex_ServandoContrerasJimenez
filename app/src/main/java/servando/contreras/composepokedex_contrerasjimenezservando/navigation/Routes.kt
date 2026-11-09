package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import kotlinx.serialization.Serializable

@Serializable
object PokemonList

@Serializable
object Login

@Serializable
object PokemonHunt

@Serializable
object Profile

@Serializable
data class PokemonDetail(val pokemon: Int)