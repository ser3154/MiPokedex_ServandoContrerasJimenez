package servando.contreras.composepokedex_contrerasjimenezservando.data

import android.content.Context
import servando.contreras.composepokedex_contrerasjimenezservando.domain.User
import servando.contreras.composepokedex_contrerasjimenezservando.preferences.ARCHIVO
import servando.contreras.composepokedex_contrerasjimenezservando.preferences.POKEMON_DISPLAY
import servando.contreras.composepokedex_contrerasjimenezservando.preferences.USER
import servando.contreras.composepokedex_contrerasjimenezservando.preferences.USERNAME

class Users(context: Context) {

    private val validUsers = listOf(
        User("servando.contreras@gmail.com", "Servando Contreras", "123"),
        User("alex.g@mail.com", "Alex Gabriel", "1234"),
        User("harry.styles@mail.com", "Harry Styles", "12345")
    )

    val shared = context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    fun getUserByMail(mail: String): User? =
        validUsers.firstOrNull { it.email.equals(mail, ignoreCase = true) }

    fun loginService(email: String, password: String): User? {
        val user = getUserByMail(email)
        if (user != null && user.password == password) {
            saveUser(user.email, user.name)
            return user
        }
        return null
    }

    fun saveUser(user: String, name: String) {
        val editor = shared.edit()
        editor.putString(USER, user)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun logout() {
        val editor = shared.edit()
        editor.remove(USER)
        editor.remove(USERNAME)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        val user = shared.getString(USER, null)
        val username = shared.getString(USERNAME, null)
        return user != null && username != null
    }

    fun getCurrentUser(): User? {
        val email = shared.getString(USER, null) ?: return null
        return getUserByMail(email)
    }

    // Preferencia lista/grid (paso 17). true = grid
    fun getPokemonDisplayGrid(): Boolean = shared.getBoolean(POKEMON_DISPLAY, false)

    fun savePokemonDisplayGrid(grid: Boolean) {
        shared.edit().putBoolean(POKEMON_DISPLAY, grid).apply()
    }
}