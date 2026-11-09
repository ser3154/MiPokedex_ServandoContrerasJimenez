package servando.contreras.composepokedex_contrerasjimenezservando.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import servando.contreras.composepokedex_contrerasjimenezservando.data.CompanionDataStore
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon

class PokemonDetailViewModel(private val companionDataStore: CompanionDataStore): ViewModel() {

    fun saveCompanion(pokemon: Pokemon){
        viewModelScope.launch {
            companionDataStore.saveCompanion(pokemon)
        }
    }
}