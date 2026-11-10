package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Pink40
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Purple40
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Typography
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

@Composable
fun ProfileScreen(innerPadding: PaddingValues, userViewModel: UserViewModel) {
    val name = userViewModel.currentUser?.name ?: ""
    val email = userViewModel.currentUser?.email ?: ""
    val pokemon by userViewModel.pokemonCompanion.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(name, style = Typography.displayLarge, color = Pink40)
        Text(email, style = Typography.bodyMedium)
        Spacer(Modifier.size(80.dp))

        Text("Mi Compañero:", style = Typography.titleLarge)

        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier.size(120.dp)
        )

        if (pokemon.number == -1) {
            Text("Aún no tienes compañero")
        } else {
            val days = userViewModel.daysAsCompanion(pokemon)
            Text(" ${pokemon.name}", style = Typography.titleLarge, color = Purple40)
            Text(
                when (days) {
                    0L -> "Es tu compañero desde hoy"
                    1L -> "Tiempo de compañero: 1 día"
                    else -> "Tiempo de compañero: $days días"
                }
            )
        }
    }
}