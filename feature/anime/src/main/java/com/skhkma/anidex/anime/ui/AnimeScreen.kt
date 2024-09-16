package com.skhkma.anidex.anime.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.skhkma.anidex.model.AnimeModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import com.skhkma.anidex.designsystem.R
import com.skhkma.anidex.designsystem.theme.AniDexTheme

@Serializable
data object AnimeRoute

fun NavGraphBuilder.animeScreen() {
    composable<AnimeRoute> {
        val viewModel: AnimeViewModel = koinViewModel()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        AnimeScreen(
            uiState = uiState.value,
            onRetry = viewModel::fetchAnimeList
        )
    }
}

@Composable
private fun AnimeScreen(
    modifier: Modifier = Modifier,
    uiState: TrendingAnimeUiState,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Popular Anime")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            if (uiState is TrendingAnimeUiState.Success) {
                LazyRow {
                    items(
                        items = uiState.animeList,
                        key = {
                            it.id
                        }
                    ) { item ->
                        Anime(
                            item = item
                        )
                    }
                }
            }
            if (uiState is TrendingAnimeUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    strokeCap = StrokeCap.Butt,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

            if (uiState is TrendingAnimeUiState.Error) {
                ErrorView(
                    modifier = Modifier.align(Alignment.Center),
                    onRetry = onRetry
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

    var isIconClicked by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .width(150.dp)
            .padding(horizontal = 4.dp, vertical = 0.dp)
    ) {
        Box() {
            AsyncImage(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp),
                model = item.image,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.place_holder_image),
            )
            IconButton(modifier = Modifier
                .align(Alignment.TopEnd),
                onClick = {
                    isIconClicked = !isIconClicked
                }
            )
            {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colorScheme.errorContainer),
                    painter = painterResource(
                        id = if (isIconClicked) {
                            R.drawable.ic_watchlist_added
                        } else {
                            R.drawable.ic_add_to_watchlist
                        }
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
        Text(
            item.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Something went wrong")
        FilledTonalButton(onClick = onRetry) {
            Text("Retry")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    AniDexTheme {
        ErrorView(
            onRetry = {}
        )
    }
}


@Preview
@Composable
fun AnimePreview() {
    AniDexTheme {
        Anime(
            item = AnimeModel(
                image = "abc",
                id = "1",
                title = "Anime Title"
            )
        )
    }
}

@Preview(apiLevel = 34, showSystemUi = true, showBackground = true, fontScale = 1.0f)
@Composable
fun AnimeScreenSuccessPreview(
    @PreviewParameter(AnimeUiStatePreviewParameterProvider::class) uiState: TrendingAnimeUiState
) {
    AniDexTheme {
        AnimeScreen(
            uiState = uiState,
            onRetry = { }
        )
    }
}
