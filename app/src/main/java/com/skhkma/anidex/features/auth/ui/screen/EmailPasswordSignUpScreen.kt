package com.skhkma.anidex.features.auth.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.AniDexTheme
import com.skhkma.anidex.common.ui.AniDexProgressButton
import com.skhkma.anidex.common.ui.ErrorDialog
import com.skhkma.anidex.features.auth.ui.viewmodel.EmailPasswordSignUpViewModel
import com.skhkma.anidex.features.auth.ui.viewmodel.EmailSignUpUiState
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
            },
            onErrorDismissClick = {
                viewModel.setUiStateToIdle()
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
    onSignUpClick: (String, String) -> Unit,
    onErrorDismissClick: () -> Unit,
) {
    Scaffold(modifier = modifier) { contentPadding ->
        Box(
            modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var email by rememberSaveable { mutableStateOf("") }
                var isEmailError by rememberSaveable { mutableStateOf(false) }

                var password by rememberSaveable { mutableStateOf("") }
                var isPasswordError by rememberSaveable { mutableStateOf(false) }
                var isPasswordShown by rememberSaveable { mutableStateOf(false) }

                EmailTextField(
                    email = email,
                    isError = isEmailError,
                    onEmailChange = {
                        email = it
                        isEmailError = !isValidEmail(it)
                    }
                )
                Spacer(modifier = Modifier.size(24.dp))
                PasswordTextField(
                    password = password,
                    isVisible = isPasswordShown,
                    isError = isPasswordError,
                    validateResult = validatePassword(password),
                    onEyeClick = { isPasswordShown = !isPasswordShown },
                    onPasswordChange = {
                        password = it
                        isPasswordError = validatePassword(password) !is ValidateResult.Success
                    }
                )
                Spacer(modifier = Modifier.size(24.dp))
                AniDexProgressButton(
                    text = "Sign Up",
                    isLoading = uiState is EmailSignUpUiState.Loading,
                    isEnable = email.isNotBlank() && password.isNotBlank()
                ) {
                    onSignUpClick(email, password)
                }
            }
            if (uiState is EmailSignUpUiState.Error) {
                ErrorDialog(
                    text = uiState.error,
                    onDismissClick = onErrorDismissClick,
                )
            }
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$"
    return email.matches(Regex(emailRegex))
}

sealed class ValidateResult {
    data object Success: ValidateResult()
    data object MinLengthError: ValidateResult()
    data object MaxLengthError: ValidateResult()
    data object SpecialCharError: ValidateResult()
    data object DigitError: ValidateResult()
    data object CharError: ValidateResult()
    data object CapitalCharError: ValidateResult()
}

private fun validatePassword(password: String): ValidateResult {
    if (password.length < 8) {
        return ValidateResult.MinLengthError
    }

    if (password.length > 40) {
        return ValidateResult.MaxLengthError
    }

    val specialCharacterRegex = Regex("[!@#$%^&*(),.?\":{}|<>]")
    val containsSpecialChar = password.any { specialCharacterRegex.containsMatchIn(it.toString()) }
    if (!containsSpecialChar) {
        return ValidateResult.SpecialCharError
    }

    val containDigits = password.any { it.isDigit() }
    if (!containDigits){
        return ValidateResult.DigitError
    }

    val containLetters = password.any { it.isLetter() }
    if (!containLetters){
        return ValidateResult.CharError
    }

    val containCapitalizedLetters = password.any { it.isLetter() && it.isUpperCase() }
    if (!containCapitalizedLetters){
        return ValidateResult.CapitalCharError
    }

    return ValidateResult.Success
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScreenLoadingPreview() {
    AniDexTheme {
        Screen(
            uiState = EmailSignUpUiState.Loading,
            onSignUpClick = { _, _ -> },
            onErrorDismissClick = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScreenErrorPreview() {
    AniDexTheme {
        Screen(
            uiState = EmailSignUpUiState.Error("Something went wrong!"),
            onSignUpClick = { _, _ -> },
            onErrorDismissClick = {},
        )
    }
}