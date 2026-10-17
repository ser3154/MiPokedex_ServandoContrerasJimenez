package servando.contreras.composepokedex_contrerasjimenezservando.model.domain


import androidx.compose.material.Icon
import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import servando.contreras.composepokedex_contrerasjimenezservando.R


enum class Destination (
    val route: String,
    val label: String,
    val icon: Int,
    val contentDestination: String
){
    HOME("PokemonList", "Home", R.drawable.home, "Pokemon Lists"),
    HUNT ("PokemonHunt", "New Pokemon", R.drawable.hunt, "Pokemon Hunt"),
    PROFILE("Profile " ,"Profile", R.drawable.profile, "Profile")
}
