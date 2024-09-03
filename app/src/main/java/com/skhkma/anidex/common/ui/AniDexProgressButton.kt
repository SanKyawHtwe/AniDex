package com.skhkma.anidex.common.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AniDexTheme

@Composable
fun AniDexProgressButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean,
    isEnable: Boolean,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Button(
            enabled = isEnable && !isLoading,
            onClick = onClick,
        ) {
            Text(
                text,
                color = if (!isLoading) MaterialTheme.colorScheme.onPrimary else Color.Transparent
            )
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp),
                strokeCap = StrokeCap.Butt,
                trackColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun AniDexProgressButtonLoadingPreview() {
    AniDexTheme {
        AniDexProgressButton(
            text = "Sign Up",
            isLoading = true,
            isEnable = true
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AniDexProgressButtonPreview() {
    AniDexTheme {
        AniDexProgressButton(
            text = "Sign Up",
            isLoading = false,
            isEnable = false
        ) {

        }
    }
}