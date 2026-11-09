package servando.contreras.composepokedex_contrerasjimenezservando.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon
import servando.contreras.composepokedex_contrerasjimenezservando.domain.preferences.PokemonSerializer

val Context.dataStore: DataStore<Pokemon> by dataStore(
    fileName = "pokemon.json",
    serializer = PokemonSerializer,
    scope = CoroutineScope(Dispatchers.IO)
)

class CompanionDataStore(private val context: Context) {

    suspend fun saveCompanion(companion: Pokemon){
        context.dataStore.updateData { pokemon ->
            companion
        }
    }

    fun pokemonCompany(): Flow<Pokemon> =
        context.dataStore.data.map { pokemon -> pokemon }
}