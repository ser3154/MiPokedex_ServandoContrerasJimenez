package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.domain.pokemonTypes
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(innerPadding: PaddingValues){
    var number = remember { TextFieldState() }
    var name = remember { TextFieldState() }
    var description = remember { TextFieldState() }
    var openType by remember { mutableStateOf(false) }
    var type = remember { TextFieldState(pokemonTypes[0]) }

    Column(Modifier.fillMaxSize().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)) {

        Text("Agregar pokemon", style = Typography.titleLarge)
        Spacer(Modifier.size(20.dp))

        TextField(state = number, label = {Text("Número de pokemon")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        TextField(state = name, label = {Text("Nombre")})

        ExposedDropdownMenuBox(expanded = openType, onExpandedChange = {openType = it}) {
            OutlinedTextField(
                readOnly = true,
                label = {Text("Selecciona un tipo")},
                state = type,
                trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = openType)},
                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )

            ExposedDropdownMenu(expanded = openType, onDismissRequest = {openType = false}) {
                pokemonTypes.forEach { t->
                    DropdownMenuItem(text = {Text(t)},
                        onClick = {type.setTextAndPlaceCursorAtEnd(t)
                            openType = false})
                }
            }
        }

        TextField(state = description, label = {Text("Descripcion")})
        Spacer(Modifier.size(15.dp))

        Button({}) {
            Text("Agregar pokemon")
        }

    }

}


@Preview(showBackground = true)
@Composable
fun AddPokemonPreview(){
    AddPokemonScreen(PaddingValues(20.dp))
}