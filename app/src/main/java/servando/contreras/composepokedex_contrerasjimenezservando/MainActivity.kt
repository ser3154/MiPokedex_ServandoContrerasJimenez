package servando.contreras.composepokedex_contrerasjimenezservando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.datastore.dataStore
import servando.contreras.composepokedex_contrerasjimenezservando.data.CompanionDataStore
import servando.contreras.composepokedex_contrerasjimenezservando.data.Users
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.RootApp
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.ComposePokedex_ContrerasJimenezServandoTheme
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.PokemonDetailViewModel
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val users = Users(this)
        val companions = CompanionDataStore(this)
        val userViewModel = UserViewModel(users, companions)
        val pokemonDetailViewModel = PokemonDetailViewModel(companions)
        setContent {
            ComposePokedex_ContrerasJimenezServandoTheme {
                Scaffold() { innerPaddibng ->
                    //MyApp(innerPaddibng)
                }
            }
        }
    }
}