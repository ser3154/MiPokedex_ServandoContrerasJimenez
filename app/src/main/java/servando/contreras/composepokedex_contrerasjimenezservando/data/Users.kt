package servando.contreras.composepokedex_contrerasjimenezservando.data

import android.content.Context
import servando.contreras.composepokedex_contrerasjimenezservando.domain.preferences.ARCHIVO
import servando.contreras.composepokedex_contrerasjimenezservando.domain.preferences.USER
import servando.contreras.composepokedex_contrerasjimenezservando.domain.preferences.USERNAME
import servando.contreras.composepokedex_contrerasjimenezservando.domain.User

class Users(context: Context) {
    val validUsers = listOf<User>(
        User("servando.contreras@gmail.com", "Servando contreras", "123"),
        User("alex.g@mail.com", "Alex gabriel", "1234"),
        User("harry.styles@mail.com", "Harry Styles", "12345")
    )

    val shared = context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    fun getUserByMail(mail: String): User?{
        val users = validUsers.filter { it.email.equals(mail, ignoreCase = true) }
        return if(users.isEmpty()) null else users.first()
    }

    fun loginService(user: String, password: String): User?{
        val user = getUserByMail(user)
        user?.let {
            if (user.password.equals(password)) {
                saveUsers(user.email, user.name)
                return user
            }
        }
        return null
    }

    private fun saveUsers(user: String, name: String){
        val editor = shared.edit()
        editor.putString(USER, user)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun logout(){
        val editor = shared.edit()
        editor.putString(USER, null)
        editor.putString(USERNAME, null)
        editor.apply()
    }

    fun isLoggedIn(): Boolean{
        val user = shared.getString(USER, null)
        val username = shared.getString(USERNAME, null)
        if (user != null && username != null){
            return true
        }
        return false
    }


    fun getCurrentUser(): User?{
        val user = shared.getString(USER, null)
        if (user != null){
            return getUserByMail(user)
        }
        return  null
    }

}