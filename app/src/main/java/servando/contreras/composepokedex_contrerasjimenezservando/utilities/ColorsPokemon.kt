package servando.contreras.composepokedex_contrerasjimenezservando.utilities

import androidx.compose.ui.graphics.Color
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.*

fun getColorByType(type: String): Pair<Color, Color> {
    return when (type.substringBefore("/").trim().lowercase()) {
        "normal" -> Normal to OffWhite
        "water" -> Water to OffWhite
        "fire" -> Fire to OffWhite
        "psych", "psychic" -> Psych to OffWhite
        "ghost" -> Ghost to OffWhite
        "bug" -> Bug to OffWhite
        "poison" -> Poison to OffWhite
        "grass" -> Grass to OffWhite
        "ground" -> Ground to OffWhite
        "rock" -> Rock to OffWhite
        "dragon" -> Dragon to OffWhite
        "dark" -> Dark to OffWhite
        "electric" -> Electric to DarkGray
        "flying" -> Flying to DarkGray
        "fight", "fighting" -> Fight to DarkGray
        "fairy" -> Fairy to DarkGray
        "ice" -> Ice to DarkGray
        else -> Normal to OffWhite
    }
}