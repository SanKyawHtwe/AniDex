package com.skhkma.anidex.features.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRoute

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize()
            .background(Color.Green)
    )
}