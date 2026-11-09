package servando.contreras.composepokedex_contrerasjimenezservando.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import servando.contreras.composepokedex_contrerasjimenezservando.data.CompanionDataStore
import servando.contreras.composepokedex_contrerasjimenezservando.data.Users
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon
import servando.contreras.composepokedex_contrerasjimenezservando.domain.User
import servando.contreras.composepokedex_contrerasjimenezservando.domain.preferences.PokemonSerializer

class UserViewModel(users: Users, private val companionDataStore: CompanionDataStore): ViewModel(){
    var currentUser by mutableStateOf<User?>(null)

    init {
        currentUser = users.getCurrentUser()
    }

    var loginError by mutableStateOf(false)
    var errorDescription by mutableStateOf<String?>(null)
    val users = users

    val pokemonCompanion: StateFlow<Pokemon> = companionDataStore.pokemonCompany().stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000), initialValue = PokemonSerializer.defaultValue)

    fun login(user: String, password: String){
        val userRetrived = users.loginService(user, password)
        if (userRetrived ==  null){
            loginError = true
            errorDescription = "Usuario o contraseña incorrectos"
        }else{
            loginError = false
            errorDescription = ""
        }
    }

    fun isLoggedIn(): Boolean{
        return users.isLoggedIn()
    }
}