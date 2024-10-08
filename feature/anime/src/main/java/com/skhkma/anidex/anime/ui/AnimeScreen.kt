package com.skhkma.anidex.anime.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.skhkma.anidex.designsystem.R
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import com.skhkma.anidex.model.AnimeModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object AnimeRoute

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.animeScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    paddingValues: PaddingValues,
    onAnimeClick: (String) -> Unit,
) {
    composable<AnimeRoute> {
        val viewModel: AnimeViewModel = koinViewModel()
        val lazyPagingItems = viewModel.flow.collectAsLazyPagingItems()
        AnimeScreen(
            pagingItems = lazyPagingItems,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope,
            paddingValues = paddingValues,
            onRetry = { lazyPagingItems.retry() },
            onAnimeClick = onAnimeClick
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun AnimeScreen(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<AnimeModel>,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    paddingValues: PaddingValues,
    onRetry: () -> Unit,
    onAnimeClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        VerticalGridSection(
            title = "Popular Animes",
            pagingItems = pagingItems,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope,
            gridContentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            ),
            onRetry = onRetry,
            onAnimeClick = onAnimeClick
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VerticalGridSection(
    modifier: Modifier = Modifier,
    title: String,
    pagingItems: LazyPagingItems<AnimeModel>,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    gridContentPadding: PaddingValues,
    onRetry: () -> Unit,
    onAnimeClick: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            var isRefreshing by remember(
                key1 = pagingItems.loadState.refresh
            ) {
                mutableStateOf(
                    !pagingItems.loadState.hasError &&
                            !pagingItems.loadState.isIdle &&
                            pagingItems.itemCount > 0
                )
            }

            PullToRefreshBox(
                modifier = Modifier.fillMaxWidth(),
                isRefreshing = isRefreshing,
                onRefresh = {
                    isRefreshing = true
                    pagingItems.refresh()
                },
                contentAlignment = Alignment.TopEnd
            ) {
                val orientation = LocalConfiguration.current.orientation

                LazyVerticalGrid(
                    columns = GridCells.Fixed(
                        if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
                    ),
                    contentPadding = gridContentPadding,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    items(
                        count = pagingItems.itemCount,
                        key = pagingItems.itemKey { it.id }
                    ) { index ->
                        pagingItems[index]?.let {
                            Anime(
                                item = it,
                                sharedTransitionScope = sharedTransitionScope,
                                animatedContentScope = animatedContentScope,
                                onClick = onAnimeClick
                            )
                        }
                    }

                    if (pagingItems.loadState.append == LoadState.Loading && pagingItems.itemCount > 0) {
                        item(span = { GridItemSpan(this.maxLineSpan) }) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .height(64.dp)
                                    .wrapContentWidth()
                            )
                        }
                    }

                    if (pagingItems.loadState.hasError && (pagingItems.itemCount > 0)) {
                        item(span = { GridItemSpan(this.maxLineSpan) }) {
                            ErrorView(
                                modifier = Modifier.align(Alignment.Center),
                                onRetry = onRetry
                            )
                        }
                    }
                }
            }

            if (pagingItems.loadState.refresh == LoadState.Loading && pagingItems.itemCount == 0) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    strokeCap = StrokeCap.Butt,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

            if (pagingItems.loadState.hasError && pagingItems.itemCount == 0) {
                ErrorView(
                    modifier = Modifier.align(Alignment.Center),
                    onRetry = onRetry
                )
            }
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Anime(
    modifier: Modifier = Modifier,
    item: AnimeModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick: (String) -> Unit
) {

    var isIconClicked by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(item.id) }
    ) {
        with(sharedTransitionScope) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-${item.id}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                        .aspectRatio(1 / 1.3f)
                        .clip(RoundedCornerShape(16.dp)),
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
                modifier = Modifier
                    .wrapContentWidth()
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "text-${item.id}"),
                        animatedVisibilityScope = animatedContentScope,
                    ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
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


//@OptIn(ExperimentalSharedTransitionApi::class)
//@Preview
//@Composable
//fun AnimePreview() {
//    AniDexTheme {
//        Anime(
//            item = AnimeModel(
//                image = "abc",
//                id = "1",
//                title = "Anime Title"
//            ),
//            onClick = {}
//        )
//    }
//}

//@Preview(apiLevel = 34, showSystemUi = true, showBackground = true, fontScale = 1.0f)
//@Composable
//fun AnimeScreenSuccessPreview(
//    @PreviewParameter(AnimeUiStatePreviewParameterProvider::class) uiState: TrendingAnimeUiState
//) {
//    AniDexTheme {
//        AnimeScreen(
//            uiState = uiState,
//            onRetry = { },
//            onAnimeClick = {}
//        )
//    }
//}
