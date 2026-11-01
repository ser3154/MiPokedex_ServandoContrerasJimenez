package servando.contreras.composepokedex_contrerasjimenezservando.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import servando.contreras.composepokedex_contrerasjimenezservando.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.domain.PokedexState
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon

class PokemonViewModel : ViewModel() {

    var state by mutableStateOf(PokedexState())
        private set

    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    var captured by mutableStateOf(false)
        private set

    fun searchPokemon() {
        gonePokemon = false
        captured = false
        wildPokemon = pokemonList.random()
    }

    fun capturePokemon() {
        val pokemon = wildPokemon ?: return
        if ((1..2).random() == 1) {
            state = state.copy(
                team = state.team + pokemon,
                lastCaptured = pokemon
            )
            captured = true
            gonePokemon = false
        } else {
            gonePokemon = true
            captured = false
        }
        wildPokemon = null
    }
}