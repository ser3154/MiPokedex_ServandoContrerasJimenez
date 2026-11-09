package servando.contreras.composepokedex_contrerasjimenezservando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import servando.contreras.composepokedex_contrerasjimenezservando.data.Users
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.RootApp
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.ComposePokedex_ContrerasJimenezServandoTheme
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val users = Users(this)
        val userViewModel = UserViewModel(users)
        setContent {
            ComposePokedex_ContrerasJimenezServandoTheme {
                Scaffold() { innerPaddibng ->
                    //MyApp(innerPaddibng)
                }
            }
        }
    }
}