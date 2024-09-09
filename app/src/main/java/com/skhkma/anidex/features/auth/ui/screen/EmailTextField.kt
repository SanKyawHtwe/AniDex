package com.skhkma.anidex.features.auth.ui.screen

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AniDexTheme

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    email: String,
    isError: Boolean,
    onEmailChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = email,
        onValueChange = onEmailChange,
        isError = isError,
        maxLines = 1,
        supportingText = {
            if (isError) Text("Invalid email address")
        },
        label = { Text("Email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldNoErrorPreview() {
    AniDexTheme {
        EmailTextField(
            email = "",
            isError = false
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmailTextFieldWithErrorPreview() {
    AniDexTheme {
        EmailTextField(
            email = "",
            isError = true
        ) { }
    }
}

