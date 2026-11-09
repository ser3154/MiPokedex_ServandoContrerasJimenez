package servando.contreras.composepokedex_contrerasjimenezservando.screens

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import servando.contreras.composepokedex_contrerasjimenezservando.data.pokemonList
import servando.contreras.composepokedex_contrerasjimenezservando.data.getPokemonByNumber
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Pink40
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Purple40
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Typography
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

@Composable
fun ProfileScreen(innerPadding: PaddingValues, userViewModel: UserViewModel) {
    val name = if (userViewModel.currentUser!= null) userViewModel.currentUser!!.name else ""
    val email = if (userViewModel.currentUser !=null) userViewModel.currentUser!!.email else ""
    val pokemon by userViewModel.pokemonCompanion.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("${name}", style = Typography.displayLarge, color = Pink40)
        Text("${email}", style = Typography.bodyMedium)
        Spacer(Modifier.size(80.dp))

        Text("Mi Compañero:",  style = Typography.titleLarge)

        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier.size(120.dp)
        )

        Text(" ${pokemon.name}", style = Typography.titleLarge, color = Purple40)
        Text("Tiempo de compañero: 2 dias")
    }

}
