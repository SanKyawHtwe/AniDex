package com.skhkma.anidex.features.auth.ui.signup.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import com.skhkma.anidex.common.ui.AniDexProgressButton
import com.skhkma.anidex.common.ui.ErrorDialog
import com.skhkma.anidex.features.auth.ui.screen.EmailTextField
import com.skhkma.anidex.features.auth.ui.screen.PasswordTextField
import com.skhkma.anidex.features.auth.ui.signup.viewmodel.EmailPasswordSignUpViewModel
import com.skhkma.anidex.features.auth.ui.signup.viewmodel.EmailSignUpUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object EmailPasswordSignUpRoute

fun NavController.navigateToEmailPasswordSignUpScreen() {
    navigate(EmailPasswordSignUpRoute)
}

fun NavGraphBuilder.emailPasswordSignUpScreen(
    onNavigateToHome: () -> Unit
) {
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
            },
            onVerifyEmailSent = { scope, snackbarHostState ->
                scope.launch {
                    snackbarHostState.showSnackbar("Verification email sent")
                }
            },
            onResume = {
                viewModel.isVerified()
            },
            onNavigateToHome = onNavigateToHome
        )
    }
}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    uiState: EmailSignUpUiState,
    onSignUpClick: (String, String) -> Unit,
    onErrorDismissClick: () -> Unit,
    onNavigateToHome: () -> Unit,
    onResume: () -> Unit,
    onVerifyEmailSent: (CoroutineScope, SnackbarHostState) -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (uiState is EmailSignUpUiState.VerificationEmailSent) {
        onVerifyEmailSent(
            scope, snackbarHostState
        )
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        // Do something with your state
        // You may want to use DisposableEffect or other alternatives
        // instead of LaunchedEffect
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                Log.d("Navigated", "Screen: on resume")
                onResume()
            }
        }
    }
    if (uiState is EmailSignUpUiState.EmailVerified){
        onNavigateToHome()
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
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
                        isEmailError = !com.skhkma.anidex.features.auth.ui.login.screen.isValidEmail(
                            it
                        )
                    }
                )
                Spacer(modifier = Modifier.size(24.dp))
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    password = password,
                    isVisible = isPasswordShown,
                    isError = isPasswordError,
                    validateResult = validatePassword(
                        password
                    ),
                    onEyeClick = { isPasswordShown = !isPasswordShown },
                    onPasswordChange = {
                        password = it
                        isPasswordError = validatePassword(
                            password
                        ) !is ValidateResult.Success
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

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$"
    return email.matches(Regex(emailRegex))
}

sealed class ValidateResult {
    data object Success : ValidateResult()
    data object MinLengthError : ValidateResult()
    data object MaxLengthError : ValidateResult()
    data object SpecialCharError : ValidateResult()
    data object DigitError : ValidateResult()
    data object CharError : ValidateResult()
    data object CapitalCharError : ValidateResult()
}

const val MINIMUM_PASSWORD_LENGTH = 8
const val MAXIMUM_PASSWORD_LENGTH = 40
fun validatePassword(password: String): ValidateResult {
    val specialCharacterRegex = Regex("[!@#$%^&*(),.?\":{}|<>]")

    return if (password.length < MINIMUM_PASSWORD_LENGTH) {
        ValidateResult.MinLengthError
    } else if (password.length > MAXIMUM_PASSWORD_LENGTH) {
        ValidateResult.MaxLengthError
    } else if (!password.any { specialCharacterRegex.containsMatchIn(it.toString()) }) {
        ValidateResult.SpecialCharError
    } else if (!password.any { it.isDigit() }) {
        ValidateResult.DigitError
    } else if (!password.any { it.isLetter() }) {
        ValidateResult.CharError
    } else if (!password.any { it.isLetter() && it.isUpperCase() }) {
        ValidateResult.CapitalCharError
    } else ValidateResult.Success
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
            onVerifyEmailSent = { _, _ -> },
            onNavigateToHome = {},
            onResume = {}
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
            onVerifyEmailSent = { _, _ -> },
            onNavigateToHome = {},
            onResume = {}
        )
    }
}
