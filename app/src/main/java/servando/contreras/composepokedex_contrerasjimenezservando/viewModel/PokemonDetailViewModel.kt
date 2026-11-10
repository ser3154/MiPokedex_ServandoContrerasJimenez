package servando.contreras.composepokedex_contrerasjimenezservando.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import servando.contreras.composepokedex_contrerasjimenezservando.data.CompanionDataStore
import servando.contreras.composepokedex_contrerasjimenezservando.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon

data class PokemonDetailUiState(
    val pokemon: Pokemon? = null,
    val previous: Pokemon? = null,
    val next: Pokemon? = null,
    val isCompanion: Boolean = false
)

class PokemonDetailViewModel(
    private val companionDataStore: CompanionDataStore,
    private val pokemonNumber: Int
) : ViewModel() {

    private val _message = Channel<String>(Channel.BUFFERED)
    val message = _message.receiveAsFlow()

    val uiState: StateFlow<PokemonDetailUiState> = companionDataStore.pokemonCompany()
        .map { companion -> buildState(companion.number) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = buildState(-1)
        )

    private fun buildState(companionNumber: Int): PokemonDetailUiState {
        val index = pokemonList.indexOfFirst { it.number == pokemonNumber }
        if (index == -1) return PokemonDetailUiState()
        return PokemonDetailUiState(
            pokemon = pokemonList[index],
            previous = pokemonList.getOrNull(index - 1),
            next = pokemonList.getOrNull(index + 1),
            isCompanion = pokemonNumber == companionNumber
        )
    }

    fun selectCompanion() {
        val pokemon = uiState.value.pokemon ?: return
        viewModelScope.launch {
            companionDataStore.saveCompanion(
                pokemon.copy(companionSince = System.currentTimeMillis())
            )
            _message.send("Compañero seleccionado")
        }
    }

    companion object {
        fun provideFactory(context: Context, pokemonNumber: Int): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    PokemonDetailViewModel(CompanionDataStore(context), pokemonNumber)
                }
            }
    }
}