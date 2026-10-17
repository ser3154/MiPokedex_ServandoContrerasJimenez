package servando.contreras.composepokedex_contrerasjimenezservando.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Electric


@Composable
fun Chip(text: String, color: Color, modifier: Modifier = Modifier, textColor: Color = Color.Unspecified) {
    Box(modifier.padding(5.dp, 2.dp)
        .background(color = color, shape = RoundedCornerShape(size = 16.dp))) {
        Text(text, color = textColor)
    }
}
@Preview(showBackground = true)
@Composable
fun ChipPreview(){
    Chip("Electrico", Electric)
}