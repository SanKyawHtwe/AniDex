package com.skhkma.anidex.features.home.ui.screen

import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AniDexTheme
import com.skhkma.anidex.R
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHomeScreen(
    navOptions: NavOptions? = null
) {
    navigate(HomeRoute, navOptions = navOptions)
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Anime : Screen("anime", R.string.anime)
    object Manga : Screen("manga", R.string.manga)
    object Watchlist : Screen("watchlist", R.string.watchlist)
    object Profile : Screen("profile", R.string.profile)
}

fun NavGraphBuilder.homeScreen(
    onNavigateToManga: () -> Unit,
    onNavigateToAuthLanding: () -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateToManga = onNavigateToManga,
            onNavigateToAuthLanding = onNavigateToAuthLanding
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToManga: () -> Unit,
    onNavigateToAuthLanding: () -> Unit
) {


    val items = listOf(
        Screen.Anime,
        Screen.Manga,
        Screen.Watchlist,
        Screen.Profile
    )

    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Face,
        Icons.Filled.Favorite,
        Icons.Filled.Person
    )
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf<Screen>(Screen.Anime) }
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedScreen = when (destination.route) {
                Screen.Anime.route -> Screen.Anime
                Screen.Manga.route -> Screen.Manga
                Screen.Watchlist.route -> Screen.Watchlist
                Screen.Profile.route -> Screen.Profile
                else -> Screen.Anime
            }
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {

            NavigationBar {


                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = Color.Gray
                        ),
                        selected = selectedScreen == screen,
                        onClick = {
                            selectedScreen = screen
                            navController.navigate(
                                when (index) {
                                    0 -> Screen.Anime
                                    1 -> Screen.Manga
                                    2 -> Screen.Watchlist
                                    3 -> Screen.Profile
                                    else -> Screen.Anime
                                }.route
                            ) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {


        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Anime.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = {
                        it / 2
                    }
                ) + fadeIn()
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = {
                        it / 2
                    }
                ) + fadeOut()
            },
            popEnterTransition = {
                slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = {
                        it / 2
                    }
                ) + fadeIn()
            },
            popExitTransition = {
                slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = {
                        it / 2
                    }
                ) + fadeOut()
            },

            ) {
            animeScreen()
            composable(route = Screen.Manga.route) {
                MangaScreen()
            }
            composable(route = Screen.Watchlist.route) {
                WatchlistScreen()
            }
            profileScreen(onNavigateToAuthLanding = onNavigateToAuthLanding)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AniDexTheme {
        HomeScreen(
            onNavigateToManga = {},
            onNavigateToAuthLanding = {}
        )
    }
}