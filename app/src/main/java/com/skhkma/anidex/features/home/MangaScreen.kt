package com.skhkma.anidex.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object MangaRoute

@Composable
fun MangaScreen(
    modifier: Modifier = Modifier
){
    Text("Manga Screen")
}