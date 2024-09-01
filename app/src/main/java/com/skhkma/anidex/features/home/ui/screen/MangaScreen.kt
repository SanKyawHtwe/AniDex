package com.skhkma.anidex.features.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AniDexTheme
import kotlinx.serialization.Serializable

@Serializable
data object MangaRoute


@Composable
fun MangaScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize()
            .background(Color.Blue)
    )
}


@Preview
@Composable
fun MangaScreenPreview() {
    AniDexTheme {

    }
}