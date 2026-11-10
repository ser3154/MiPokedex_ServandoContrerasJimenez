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
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    onNavigateToHome: () -> Unit,
    userViewModel: UserViewModel
) {
    var user by remember { mutableStateOf("") }
    val password = remember { TextFieldState() }

    // Si ya hay sesión guardada, pasa directo al home
    LaunchedEffect(Unit) {
        if (userViewModel.isLoggedIn()) onNavigateToHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(Modifier.size(15.dp))

        SecureTextField(
            state = password,
            textObfuscationMode = TextObfuscationMode.RevealLastTyped,
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(Modifier.size(25.dp))

        Button(onClick = {
            userViewModel.login(user, password.text.toString())
            if (!userViewModel.loginError) {
                onNavigateToHome()
            }
        }) {
            Text("Entrar")
        }

        if (userViewModel.loginError) {
            Spacer(Modifier.size(10.dp))
            Text(
                userViewModel.errorDescription ?: "",
                color = Color.Red,
                fontStyle = FontStyle.Italic
            )
        }
    }
}