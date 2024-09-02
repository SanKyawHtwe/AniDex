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
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.skhkma.anidex.R
import com.skhkma.anidex.features.auth.ui.screen.EmailPasswordSignUpRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHomeScreen() {
    navigate(HomeRoute)
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Anime : Screen("anime", R.string.anime)
    object Manga : Screen("manga", R.string.manga)
    object Watchlist : Screen("watchlist", R.string.watchlist)
    object Profile : Screen("profile", R.string.profile)
}

fun NavGraphBuilder.homeScreen(
    onNavigateToManga: () -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateToManga = onNavigateToManga
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToManga: () -> Unit
) {

    var selectedItem by remember { mutableIntStateOf(0) }
//    val items = listOf("Anime", "Manga", "Favourite", "Profile")

    val items = listOf(
        Screen.Anime,
        Screen.Manga,
        Screen.Watchlist,
        Screen.Profile
    )

    val navController = rememberNavController()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {

            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Face, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(
                                when (index) {
                                    0 -> AnimeRoute
                                    1 -> MangaRoute
                                    2 -> WatchlistRoute
                                    3 -> ProfileRoute
                                    else -> AnimeRoute
                                }
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
            startDestination = AnimeRoute,
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
            composable<MangaRoute> {
                MangaScreen()
            }
            composable<WatchlistRoute> {
                WatchlistScreen()
            }
            composable<ProfileRoute> {
                ProfileScreen()
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    AniDexTheme {
//        HomeScreen(
//            onNavigateToManga = {}
//        )
//    }
//}