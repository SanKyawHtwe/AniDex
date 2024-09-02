package com.skhkma.anidex.features.auth.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.AniDexTheme
import com.skhkma.anidex.common.ui.AniDexProgressButton
import com.skhkma.anidex.features.auth.ui.EmailPasswordSignUpViewModel
import com.skhkma.anidex.features.auth.ui.EmailSignUpUiState
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object EmailPasswordSignUpRoute

fun NavGraphBuilder.emailPasswordSignUpScreen() {
    composable<EmailPasswordSignUpRoute> {
        val viewModel: EmailPasswordSignUpViewModel = koinViewModel()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        Screen(
            uiState = uiState.value,
            onSignUpClick = { email, password ->
                viewModel.createAccount(email, password)
            }
        )
    }
}

fun NavController.navigateToEmailPasswordSignUpScreen() {
    navigate(EmailPasswordSignUpRoute)
}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    uiState: EmailSignUpUiState,
    onSignUpClick: (String, String) -> Unit
) {
    Scaffold(modifier = modifier) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var email by rememberSaveable { mutableStateOf("") }

            var password by rememberSaveable { mutableStateOf("") }

            var isPasswordShown by rememberSaveable { mutableStateOf(false) }

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                )
            )
            Spacer(modifier = Modifier.size(24.dp))
            PasswordTextField(
                password = password,
                isVisible = isPasswordShown,
                onEyeClick = { isPasswordShown = !isPasswordShown },
                onPasswordChange = { password = it }
            )
            Spacer(modifier = Modifier.size(24.dp))
            AniDexProgressButton(
                text = "Sign Up",
                isLoading = uiState is EmailSignUpUiState.Loading
            ) {
                onSignUpClick(email, password)
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScreenLoadingPreview() {
    AniDexTheme {
        Screen(
            uiState = EmailSignUpUiState.Loading,
            onSignUpClick = { _, _ -> }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScreenPreview() {
    AniDexTheme {
        Screen(
            uiState = EmailSignUpUiState.Success(""),
            onSignUpClick = { _, _ -> }
        )
    }
}