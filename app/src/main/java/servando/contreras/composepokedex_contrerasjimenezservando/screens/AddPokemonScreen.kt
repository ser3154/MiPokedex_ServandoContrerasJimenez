package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import servando.contreras.composepokedex_contrerasjimenezservando.domain.pokemonTypes
import servando.contreras.composepokedex_contrerasjimenezservando.ui.theme.Typography
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.AddPokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(innerPadding: PaddingValues, addPokemonViewModel: AddPokemonViewModel) {
    val number = remember { TextFieldState() }
    val name = remember { TextFieldState() }
    val description = remember { TextFieldState() }
    var openType by remember { mutableStateOf(false) }
    val type = remember { TextFieldState(pokemonTypes[0]) }

    val pokemonList by addPokemonViewModel.pokemons.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        addPokemonViewModel.loadPokemons()
    }

    Column(
        Modifier.fillMaxSize().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Text("Agregar pokemon", style = Typography.titleLarge)
        Spacer(Modifier.size(20.dp))

        TextField(
            state = number,
            label = { Text("Número de pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(state = name, label = { Text("Nombre") })

        ExposedDropdownMenuBox(expanded = openType, onExpandedChange = { openType = it }) {
            OutlinedTextField(
                readOnly = true,
                label = { Text("Selecciona un tipo") },
                state = type,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openType) },
                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )

            ExposedDropdownMenu(expanded = openType, onDismissRequest = { openType = false }) {
                pokemonTypes.forEach { t ->
                    DropdownMenuItem(
                        text = { Text(t) },
                        onClick = {
                            type.setTextAndPlaceCursorAtEnd(t)
                            openType = false
                        }
                    )
                }
            }
        }

        TextField(state = description, label = { Text("Descripcion") })
        Spacer(Modifier.size(15.dp))

        Button(onClick = {
            addPokemonViewModel.savePokemon(
                name = name.text.toString(),
                type = type.text.toString(),
                number = number.text.toString().toIntOrNull() ?: 0,
                description = description.text.toString()
            )
        }) {
            Text("Agregar pokemon")
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemonList) { pokemon ->
                Text(pokemon.toString())
            }
        }
    }
}