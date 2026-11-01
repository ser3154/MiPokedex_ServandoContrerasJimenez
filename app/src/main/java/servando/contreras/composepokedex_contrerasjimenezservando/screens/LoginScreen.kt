package servando.contreras.composepokedex_contrerasjimenezservando.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    innerPadding: PaddingValues,
    viewModel: UserViewModel,
    onLoginSuccess: () -> Unit
) {
    // Paso 16: si ya hay sesión guardada, saltamos directo al menú
    val alreadyLogged = remember { viewModel.isLoggedIn() }
    LaunchedEffect(alreadyLogged) {
        if (alreadyLogged) onLoginSuccess()
    }

    if (!alreadyLogged) {
        var email by remember { mutableStateOf("") }
        val passwordState = rememberTextFieldState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            SecureTextField(
                state = passwordState,
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = {
                viewModel.login(email.trim(), passwordState.text.toString())
                if (!viewModel.loginError) onLoginSuccess()
            }) {
                Text("Iniciar sesión")
            }
            if (viewModel.loginError) {
                Text(
                    text = viewModel.errorDescription,
                    color = Color.Red,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}