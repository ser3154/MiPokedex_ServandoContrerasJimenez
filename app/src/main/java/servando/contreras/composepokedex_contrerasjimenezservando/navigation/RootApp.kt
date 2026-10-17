package servando.contreras.composepokedex_contrerasjimenezservando.navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import servando.contreras.composepokedex_contrerasjimenezservando.model.domain.Destination

@Composable
fun NavigationBar(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val startDestination = "PokemonList"
    val selectedDestination by rememberSaveable { }
    //val startDestination = Destination.HOME
    //val selectedDestination by remember { mutableStateOf(startDestination) }

    Scaffold(modifier = Modifier.fillMaxWidth(), bottomBar ={
        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets){
            Destination.entries.forEachIndexed { index, destination ->
                NavigationBarItem(selected = selectedDestination == index, onClick = {navController.navigate(route = destination.route), selectedDestination = index},
                    icon = Icon(painterResource(destination.icon), ))
            }
        }
    }) {
        innerPadding ->
        MyApp(innerPadding)
    }

}