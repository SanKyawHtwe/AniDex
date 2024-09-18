package com.skhkma.anidex.anime.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.skhkma.anidex.designsystem.R
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import com.skhkma.anidex.model.EpisodeModel
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
    val mockEpisodes = listOf(
        EpisodeModel(
            id = "0",
            thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
            number = 1,
            title = "Dummy episode title one"
        ),
        EpisodeModel(
            id = "1",
            thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
            number = 2,
            title = "Dummy episode title two"
        ),
        EpisodeModel(
            id = "2",
            thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
            number = 3,
            title = "Dummy episode title three"
        ),
        EpisodeModel(
            id = "3",
            thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
            number = 4,
            title = "Dummy episode title four"
        ),
        EpisodeModel(
            id = "4",
            thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
            number = 5,
            title = "Dummy episode title five"
        ),
        EpisodeModel(
            id = "5",
            thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
            number = 6,
            title = "Dummy episode title six"
        ),
    )
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        items(
            mockEpisodes,
            key = { episode -> episode.id }
        ) {
            Episode(
                episode = it
            )
        }
    }
}

@Composable
fun Episode(modifier: Modifier = Modifier, episode: EpisodeModel) {
    Column(modifier = modifier.padding(bottom = 12.dp)) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            model = episode.thumbnail,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.place_holder_image),
        )
        Row {
            Text(
                text = "Episode ${episode.number}"
            )
            Text(
                text = episode.title,
                modifier = Modifier.padding(start = 4.dp),
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EpisodePreview() {
    AniDexTheme {
        Episode(
            episode = EpisodeModel(
                id = "0",
                thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
                number = 1,
                title = "Dummy episode title one"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimeEpisodesScreenPreview() {
    AniDexTheme {
        AnimeEpisodesScreen()
    }
}