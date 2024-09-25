package com.skhkma.anidex.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import kotlinx.serialization.Serializable

@Serializable
data object MangaRoute

fun NavGraphBuilder.mangaScreen() {
    composable<MangaRoute> {
        MangaScreen()
    }
}

@Composable
private fun MangaScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
    )
}

@Preview
@Composable
private fun MangaScreenPreview() {
    AniDexTheme {

    }
}