package com.skhkma.anidex.features.auth.ui.screen.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import com.skhkma.anidex.common.ui.AniDexProgressButton
import com.skhkma.anidex.common.ui.ErrorDialog
import com.skhkma.anidex.features.auth.ui.screen.EmailTextField
import com.skhkma.anidex.features.auth.ui.screen.PasswordTextField
import com.skhkma.anidex.features.auth.ui.screen.login.viewmodel.EmailPasswordLoginUiState
import com.skhkma.anidex.features.auth.ui.screen.login.viewmodel.EmailPasswordLoginViewModel
import com.skhkma.anidex.features.auth.ui.screen.signup.ValidateResult
import com.skhkma.anidex.features.auth.ui.screen.signup.validatePassword
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object EmailPasswordLoginRoute

fun NavController.navigateToEmailPasswordLoginScreen() {
    navigate(EmailPasswordLoginRoute)
}

fun NavGraphBuilder.emailPasswordLoginScreen(
    onNavigateToHome: () -> Unit
) {
    composable<EmailPasswordLoginRoute> {
        val viewModel: EmailPasswordLoginViewModel = koinViewModel()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        Screen(
            uiState = uiState.value,
            onLoginClick = viewModel::login,
            onErrorDismissClick = {
                viewModel.setUiStateToIdle()
            },
            onNavigateToHome = onNavigateToHome
        )
    }
}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    uiState: EmailPasswordLoginUiState,
    onLoginClick: (String, String) -> Unit,
    onErrorDismissClick: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    if (uiState is EmailPasswordLoginUiState.Success) {
        onNavigateToHome()
    }

    Scaffold(
        modifier = modifier,

        ) { contentPadding ->
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
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    email = email,
                    isError = isEmailError,
                    onEmailChange = {
                        email = it
                        isEmailError = !isValidEmail(it)
                    }
                )
                Spacer(modifier = Modifier.size(24.dp))
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 40.dp),
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
                    text = "Login",
                    isLoading = uiState is EmailPasswordLoginUiState.Loading,
                    isEnable = email.isNotBlank() && password.isNotBlank()
                ) {
                    onLoginClick(email, password)
                }
            }
            if (uiState is EmailPasswordLoginUiState.Error) {
                ErrorDialog(
                    text = uiState.error,
                    onDismissClick = onErrorDismissClick,
                )
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$"
    return email.matches(Regex(emailRegex))
}

//sealed class ValidateResult {
//    data object Success : ValidateResult()
//    data object MinLengthError : ValidateResult()
//    data object MaxLengthError : ValidateResult()
//    data object SpecialCharError : ValidateResult()
//    data object DigitError : ValidateResult()
//    data object CharError : ValidateResult()
//    data object CapitalCharError : ValidateResult()
//}
//
//const val MINIMUM_PASSWORD_LENGTH = 8
//const val MAXIMUM_PASSWORD_LENGTH = 40
//fun validatePassword(password: String): ValidateResult {
//    if (password.length < MINIMUM_PASSWORD_LENGTH) {
//        return ValidateResult.MinLengthError
//    }
//
//    if (password.length > MAXIMUM_PASSWORD_LENGTH) {
//        return ValidateResult.MaxLengthError
//    }
//
//    val specialCharacterRegex = Regex("[!@#$%^&*(),.?\":{}|<>]")
//    val containsSpecialChar = password.any { specialCharacterRegex.containsMatchIn(it.toString()) }
//    if (!containsSpecialChar) {
//        return ValidateResult.SpecialCharError
//    }
//
//    val containDigits = password.any { it.isDigit() }
//    if (!containDigits) {
//        return ValidateResult.DigitError
//    }
//
//    val containLetters = password.any { it.isLetter() }
//    if (!containLetters) {
//        return ValidateResult.CharError
//    }
//
//    val containCapitalizedLetters = password.any { it.isLetter() && it.isUpperCase() }
//    if (!containCapitalizedLetters) {
//        return ValidateResult.CapitalCharError
//    }
//
//    return ValidateResult.Success
//}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScreenLoadingPreview() {
    AniDexTheme {
        Screen(
            uiState = EmailPasswordLoginUiState.Loading,
            onLoginClick = { _, _ -> },
            onErrorDismissClick = {},
            onNavigateToHome = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScreenErrorPreview() {
    AniDexTheme {
        Screen(
            uiState = EmailPasswordLoginUiState.Error("Something went wrong!"),
            onLoginClick = { _, _ -> },
            onErrorDismissClick = {},
            onNavigateToHome = {}
        )
    }
}