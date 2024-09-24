@file:OptIn(ExperimentalMaterial3Api::class)

package com.skhkma.anidex.anime.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import coil.compose.AsyncImage
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.Status
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable
data class AnimeDetailRoute(val id: String)

fun NavGraphBuilder.animeDetailScreen(
    onNavigateUp: () -> Unit
) {
    composable<AnimeDetailRoute> { backStackEntry ->
        val animeDetailRoute: AnimeDetailRoute = backStackEntry.toRoute()
        val viewModel: AnimeDetailViewModel = koinViewModel {
            parametersOf(animeDetailRoute.id)
        }
        val detailUiState = viewModel.detailUiState.collectAsStateWithLifecycle()
        val episodesUiState = viewModel.episodesUiState.collectAsStateWithLifecycle()
        val categoryUiState = viewModel.animeCategoryUiState.collectAsStateWithLifecycle()
        AnimeDetailScreen(
            detailUiState = detailUiState.value,
            episodesUiState = episodesUiState.value,
            categoryUiState = categoryUiState.value,
            onNavigateUp = onNavigateUp
        )
    }
}

fun NavController.navigateToAnimeDetail(id: String) {
    navigate(AnimeDetailRoute(id))
}

private data class AnimeDetailTabRoute<T : Any>(val name: String, val route: T)

private val animeDetailTabRoute = listOf(
    AnimeDetailTabRoute("Summary", AnimeDetailSummaryRoute),
    AnimeDetailTabRoute("Episodes", AnimeEpisodesRoute)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    modifier: Modifier = Modifier,
    detailUiState: AnimeDetailUiState,
    episodesUiState: EpisodesUiState,
    categoryUiState: AnimeCategoryUiState,
    onNavigateUp: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AnimeDetailsAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = null,
                scrollBehavior = scrollBehavior,
                onNavigateUp = onNavigateUp,
                onFavouriteClick = {}
            )
        }
    ) { contentPadding ->
        if (detailUiState is AnimeDetailUiState.Success) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Header(
                    scrollState = scrollState,
                    coverImage = detailUiState.anime.coverImage,
                    title = detailUiState.anime.title
                )
                TabsAndPager(
                    detailModel = detailUiState.anime,
                    episodesUiState = episodesUiState,
                    categoryUiState = categoryUiState
                )
                Spacer(
                    modifier = Modifier.size(contentPadding.calculateBottomPadding().plus(24.dp))
                )
            }
        }

        if (detailUiState is AnimeDetailUiState.Loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    strokeCap = StrokeCap.Butt,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    coverImage: String,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.6f)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationY = 0.5f * scrollState.value
                },
            model = coverImage,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(
                id = com.skhkma.anidex.designsystem.R.drawable.place_holder_image
            ),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.background,
                            Color.Transparent,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )
        Text(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .align(Alignment.BottomStart),
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun TabsAndPager(
    modifier: Modifier = Modifier,
    detailModel: AnimeDetailModel,
    episodesUiState: EpisodesUiState,
    categoryUiState: AnimeCategoryUiState
) {
    var tabState by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        animeDetailTabRoute.size
    }

    LaunchedEffect(tabState) {
        pagerState.animateScrollToPage(tabState)
    }
    LaunchedEffect(pagerState.currentPage) {
        tabState = pagerState.currentPage
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        SecondaryTabRow(
            selectedTabIndex = tabState,
        ) {
            animeDetailTabRoute.forEachIndexed { index, animeDetailTabRoute ->
                Tab(
                    onClick = {
                        tabState = index
                    },
                    selected = tabState == index,
                    text = {
                        Text(
                            text = animeDetailTabRoute.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                )
            }
        }
        HorizontalPager(
            state = pagerState,
        ) { index ->
            if (index == 0) {
                AnimeDetailSummaryScreen(
                    anime = detailModel,
                    categoryUiState = categoryUiState
                )
            } else {
                AnimeEpisodesScreen(
                    uiState = episodesUiState
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnimeDetailsAppBar(
    title: String?,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigateUp: () -> Unit,
    onFavouriteClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if (title != null) {
                Text(text = title)
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0f),
            scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
        ),
        navigationIcon = {
            IconButton(
                onClick = onNavigateUp
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = onFavouriteClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}


@Preview
@Composable
private fun Preview() {
    AniDexTheme {
        AnimeDetailScreen(
            detailUiState = AnimeDetailUiState.Success(
                AnimeDetailModel(
                    id = "0",
                    title = "Cowboy Bebop",
                    coverImage = "https://images.alphacoders.com/136/1361559.jpeg",
                    posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6F_0YOA3QEJIjPoJAS_gUMv6_N5X-Dt_fLw&s",
                    averageRating = "88.99%",
                    type = "TV",
                    status = Status.FINISHED,
                    startDate = "1998-04-03",
                    ageRating = "R",
                    description = "In the year 2071, humanity has colonoized several of the planets and moons..."
                )
            ),
            episodesUiState = EpisodesUiState.Loading,
            categoryUiState = AnimeCategoryUiState.Loading,
            onNavigateUp = {}
        )
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    AniDexTheme {
        AnimeDetailScreen(
            detailUiState = AnimeDetailUiState.Loading,
            episodesUiState = EpisodesUiState.Loading,
            categoryUiState = AnimeCategoryUiState.Loading,
            onNavigateUp = {}
        )
    }
}