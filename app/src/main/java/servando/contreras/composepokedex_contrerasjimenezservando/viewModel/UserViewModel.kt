package servando.contreras.composepokedex_contrerasjimenezservando.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import servando.contreras.composepokedex_contrerasjimenezservando.data.Users
import servando.contreras.composepokedex_contrerasjimenezservando.domain.User

class UserViewModel(users: Users): ViewModel(){
    var currentUser by mutableStateOf<User?>(null)

    init {
        currentUser = users.getCurrentUser()
    }

    var loginError by mutableStateOf(false)
    var errorDescription by mutableStateOf(null)
    val users = users

    fun login(user: String, password: String){
        val userRetrived = users.loginService(user, password)
        if (userRetrived ==  null){
            loginError = true
            errorDescription = "Usuareio o contraseña incorrectos"
        }else{
            loginError = false
            errorDescription = ""
        }
    }

    fun isLoggedIn(): Boolean{
        return users.isLoggedIn()
    }
}