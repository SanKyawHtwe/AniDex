package com.skhkma.anidex.features.auth.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AniDexTheme

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    isVisible: Boolean = false,
    isError: Boolean,
    validateResult: ValidateResult,
    onEyeClick: () -> Unit,
    onPasswordChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChange,
        isError = isError,
        supportingText = {
            if (isError) Text(getErrorMessage(validateResult))
        },
        label = { Text("Password") },
        singleLine = true,
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        trailingIcon = {
            Icon(
                Icons.Filled.Info,
                contentDescription = "Localized description",
                modifier = Modifier.clickable {
                    onEyeClick()
                }
            )
        },
    )
}

private fun getErrorMessage(validateResult: ValidateResult): String {
    return when (validateResult) {
        ValidateResult.MinLengthError -> "Minimum length must be 8."
        ValidateResult.MaxLengthError -> "Maximum length must be 40."
        ValidateResult.SpecialCharError -> "Must include one special character."
        ValidateResult.DigitError -> "Must include one digit."
        ValidateResult.CharError -> "Must include one character."
        ValidateResult.CapitalCharError -> "Must include one capitalized character."
        ValidateResult.Success -> ""
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldSuccessPreview() {
    AniDexTheme {
        PasswordTextField(
            password = "",
            isError = false,
            validateResult = ValidateResult.Success,
            onEyeClick = {},
            onPasswordChange = { _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldWithErrorPreview() {
    AniDexTheme {
        PasswordTextField(
            password = "",
            isError = true,
            validateResult = ValidateResult.MinLengthError,
            onEyeClick = {},
            onPasswordChange = { _ -> }
        )
    }
}