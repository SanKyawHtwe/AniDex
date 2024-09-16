package com.skhkma.anidex.profile.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import kotlinx.serialization.Serializable

@Serializable
data object FavouriteAnimesRoute

fun NavGraphBuilder.favouriteAnimesScreen() {
    composable<FavouriteAnimesRoute> {
        BackHandler(enabled = true) {

        }
        FavouriteAnimes()
    }
}

@Composable
fun FavouriteAnimes(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("This is anime tab")
    }
}

@Preview
@Composable
private fun Preview() {
    AniDexTheme {
        FavouriteAnimes()
    }
}
