package com.skhkma.anidex.profile.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object FavouriteMangasRoute

fun NavGraphBuilder.favouriteMangasScreen(
) {
    composable<FavouriteMangasRoute> {
        BackHandler(enabled = true) {

        }
        FavouriteMangas()
    }
}

@Composable
fun FavouriteMangas(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("This is manga tab")
    }
}

