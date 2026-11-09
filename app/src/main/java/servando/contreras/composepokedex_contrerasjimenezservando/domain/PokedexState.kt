package servando.contreras.composepokedex_contrerasjimenezservando.domain

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lastCaptured: Pokemon? = null
)