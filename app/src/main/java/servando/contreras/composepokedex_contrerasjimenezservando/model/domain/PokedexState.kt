package servando.contreras.composepokedex_contrerasjimenezservando.model.domain

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lastCaptured: Pokemon? = null
)
