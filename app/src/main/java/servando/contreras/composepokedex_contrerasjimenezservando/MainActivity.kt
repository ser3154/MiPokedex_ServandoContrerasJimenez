package servando.contreras.composepokedex_contrerasjimenezservando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import servando.contreras.composepokedex_contrerasjimenezservando.data.Users
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.RootApp
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.ComposePokedex_ContrerasJimenezServandoTheme
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        viewModelFactory {
            initializer { UserViewModel(Users(applicationContext)) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePokedex_ContrerasJimenezServandoTheme {
                RootApp(userViewModel)
            }
        }
    }
}