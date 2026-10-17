package servando.contreras.composepokedex_contrerasjimenezservando.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import servando.contreras.composepokedex_contrerasjimenezservando.model.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.model.domain.Pokemon


class PokemonViewModel: ViewModel() {

    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemon by mutableStateOf(listOf<Pokemon>())
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    var captured by mutableStateOf(false)

    fun searchPokemon(){
        gonePokemon= false
        captured = false
        wildPokemon = pokemonList.random()
    }

    fun capturePokemon(){
        wildPokemon?.let {
            val isCaptured = (1..2).random()
            if (isCaptured == 1){
                capturedPokemon = capturedPokemon + it
                gonePokemon = false
                wildPokemon = null
                captured = false
            }else{
                gonePokemon = true
                wildPokemon = null
            }
        }
    }
}