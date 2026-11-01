package servando.contreras.composepokedex_contrerasjimenezservando.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import servando.contreras.composepokedex_contrerasjimenezservando.data.Users

class UserViewModel(private val users: Users) : ViewModel() {

    var loginError by mutableStateOf(false)
        private set

    var errorDescription by mutableStateOf("")
        private set

    fun login(email: String, password: String) {
        val user = users.loginService(email, password)
        if (user == null) {
            loginError = true
            errorDescription = "Usuario o contraseña incorrectos"
        } else {
            loginError = false
            errorDescription = ""
        }
    }

    fun isLoggedIn(): Boolean = users.isLoggedIn()

    fun logout() = users.logout()

    fun getPokemonDisplayGrid(): Boolean = users.getPokemonDisplayGrid()

    fun savePokemonDisplayGrid(grid: Boolean) = users.savePokemonDisplayGrid(grid)
}