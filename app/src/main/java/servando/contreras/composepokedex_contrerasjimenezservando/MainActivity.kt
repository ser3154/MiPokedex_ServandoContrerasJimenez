package servando.contreras.composepokedex_contrerasjimenezservando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import servando.contreras.composepokedex_contrerasjimenezservando.navigation.RootApp
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.ComposePokedex_ContrerasJimenezServandoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePokedex_ContrerasJimenezServandoTheme {
                RootApp()
            }
        }
    }
}