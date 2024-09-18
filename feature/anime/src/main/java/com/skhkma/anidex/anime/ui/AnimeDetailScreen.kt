package com.skhkma.anidex.anime.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skhkma.anidex.anime.R
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import com.skhkma.anidex.designsystem.R as designR

@Serializable
data object AnimeDetailRoute

fun NavGraphBuilder.animeDetailScreen() {
    composable<AnimeDetailRoute> {
        val viewModel: AnimeViewModel = koinViewModel()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        AnimeDetailScreen(
        )
    }
}

private data class AnimeDetailTabRoute<T : Any>(val name: String, val route: T)

private val animeDetailTabRoute = listOf(
    AnimeDetailTabRoute("Summary", AnimeDetailSummaryRoute),
    AnimeDetailTabRoute("Episodes", AnimeEpisodesRoute)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            AnimeDetailsAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = null,
                scrollBehavior = scrollBehavior,
                onNavigateUp = { },
                onFavouriteClick = {}
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    painter = painterResource(designR.drawable.anime_cover),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text(
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 20.dp)
                        .align(Alignment.BottomStart),
                    text = "Attack On Titans",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

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

            Column {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = pagerState,
                ) { index ->
                    if (index == 0) {
                        AnimeDetailSummaryScreen()
                    } else {
                        AnimeEpisodesScreen()
                    }
                }
            }
//            NavHost(
//                modifier = Modifier.fillMaxWidth()
//                    .padding(top = 12.dp),
//                navController = navController,
//                startDestination = AnimeDetailSummaryRoute,
//            ) {
//                animeDetailSummaryScreen()
//                animeEpisodesScreen()
//            }
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

        )
    }
}