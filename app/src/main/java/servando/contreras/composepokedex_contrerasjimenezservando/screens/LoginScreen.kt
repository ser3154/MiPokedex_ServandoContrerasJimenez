package servando.contreras.composepokedex_contrerasjimenezservando.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import servando.contreras.composepokedex_contrerasjimenezservando.viewModel.UserViewModel

@Composable
fun LoginScreen(innerPadding: PaddingValues, onNavigateToHome: () -> Unit, userViewModel: UserViewModel = viewModel()) {

    var user by remember { mutableStateOf("") }
    var password = remember { TextFieldState() }

    if (userViewModel.isLoggedIn()) {
        onNavigateToHome

    } else {


        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = user, onValueChange = { user = it },
                label = { Text("Correo electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
        Spacer(Modifier.size(15.dp))

        SecureTextField(
            state = password, textObfuscationMode = TextObfuscationMode.RevealLastTyped,
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.size(25.dp))

        Button(onClick = {
            userViewModel.login(user, password.text as String)
            if (userViewModel.loginError) {
                onNavigateToHome
            }
        }) {
            Text("Entrar")
        }
        if (userViewModel.loginError) {
            Text(userViewModel.errorDescription?: "", color = Color.Red, fontStyle = FontStyle.Italic)
        }
    }
}


    @Preview(showBackground = true)
    @Composable
    fun LoginPreview() {
        LoginScreen(PaddingValues(0.dp), {})
    }
