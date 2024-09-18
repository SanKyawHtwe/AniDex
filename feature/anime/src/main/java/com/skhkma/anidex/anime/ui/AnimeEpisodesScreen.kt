package com.skhkma.anidex.anime.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skhkma.anidex.designsystem.R as designR
import kotlinx.serialization.Serializable

@Serializable
data object AnimeEpisodesRoute

fun NavGraphBuilder.animeEpisodesScreen() {
    composable<AnimeEpisodesRoute> {
        AnimeEpisodesScreen()
    }
}

@Composable
fun AnimeEpisodesScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        LazyColumn {

        }
    }
}

@Composable
fun Episode(modifier: Modifier = Modifier) {
    Box (modifier = modifier){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            painter = painterResource(designR.drawable.anime_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Row {
            Text(
                text = "Episode 1"
            )
            Text(
                text = "This is the title of the episode",
                modifier = Modifier.padding(start = 4.dp),
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}