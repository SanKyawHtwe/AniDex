package com.skhkma.anidex.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.AniDexTheme
import com.skhkma.anidex.ui.viewmodel.AnimeViewModel
import kotlinx.serialization.Serializable
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.skhkma.anidex.R
import com.skhkma.anidex.data.model.AnimeModel

@Serializable
data object AnimeRoute

@Composable
fun AnimeScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimeViewModel = viewModel(),
) {
    val animeList = viewModel.animeList.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Anime")

        LazyRow{

            items(animeList.value) { item ->
                Anime(
                    item = item
                )
            }
        }
    }
}

@Composable
fun Anime(
    modifier: Modifier = Modifier,
    item: AnimeModel
) {
    AsyncImage(
        modifier = modifier
            .width(150.dp)
            .height(200.dp),
        model = item.image,
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.place_holder_image),
    )
}

@Preview
@Composable
fun AnimePreview() {
    AniDexTheme {
        Anime(
            item = AnimeModel(
                image = "abc",
                id = 1,
                title = "Anime Title"
            )
        )
    }
}

@Preview
@Composable
fun AnimeScreenPreview() {
    AniDexTheme {
        AnimeScreen()
    }
}