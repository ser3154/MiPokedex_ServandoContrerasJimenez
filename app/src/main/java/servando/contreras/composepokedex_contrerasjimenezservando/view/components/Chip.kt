package servando.contreras.composepokedex_contrerasjimenezservando.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Grass
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.OffWhite

@Composable
fun NumberChip(
    text: String,
    modifier: Modifier = Modifier,
    colors: Pair<Color, Color>
) {
    Row(
        modifier = modifier
            .size(30.dp)
            .background(colors.first, CircleShape)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Black,
            color = colors.second,
            softWrap = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NumberChipPreview() {
    NumberChip("1", colors = Grass to OffWhite)
}