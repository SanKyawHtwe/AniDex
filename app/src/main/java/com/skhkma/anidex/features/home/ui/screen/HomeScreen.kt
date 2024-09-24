package com.skhkma.anidex.features.home.ui.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.skhkma.anidex.anime.ui.AnimeRoute
import com.skhkma.anidex.anime.ui.animeScreen
import com.skhkma.anidex.anime.ui.navigateToAnimeDetail
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import com.skhkma.anidex.profile.ui.ProfileRoute
import com.skhkma.anidex.profile.ui.profileScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavController.navigateToHomeScreen(
    navOptions: NavOptions? = null
) {
    navigate(HomeRoute, navOptions = navOptions)
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.homeScreen(
    onNavigateToManga: () -> Unit,
    onNavigateToAuthLanding: () -> Unit,
    onAnimeClick: (String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateToManga = onNavigateToManga,
            onNavigateToAuthLanding = onNavigateToAuthLanding,
            onAnimeClick = onAnimeClick,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this
        )
    }
}

private data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

private val topLevelRoutes = listOf(
    TopLevelRoute("Anime", AnimeRoute, Icons.Filled.Home),
    TopLevelRoute("Manga", MangaRoute, Icons.Filled.Face),
    TopLevelRoute("Watchlist", WatchlistRoute, Icons.Filled.Favorite),
    TopLevelRoute("Profile", ProfileRoute, Icons.Filled.Person)
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToManga: () -> Unit,
    onNavigateToAuthLanding: () -> Unit,
    onAnimeClick: (String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = modifier,
//        topBar = {
//           if( currentDestination?.hierarchy?.any {
//                   it.hasRoute(topLevelRoutes.last().route::class) } == true){
//               ProfileTopAppBar(
//                   modifier = Modifier,
//                   scrollBehavior = scrollBehavior
//               )
//           }
//        },
        bottomBar = {
            NavigationBar {

                topLevelRoutes.forEach { topLevelRoute ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                topLevelRoute.icon,
                                contentDescription = topLevelRoute.name
                            )
                        },
                        label = { Text(topLevelRoute.name) },
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute(topLevelRoute.route::class)
                        } == true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = Color.Gray
                        ),
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
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
            startDestination = topLevelRoutes.first().route,
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
            animeScreen(
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                onAnimeClick = onAnimeClick
            )
            mangaScreen()
            watchlistScreen()
            profileScreen(onNavigateToAuthLanding = onNavigateToAuthLanding)
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun HomeScreenPreview() {
//    AniDexTheme {
//        HomeScreen(
//            onNavigateToManga = {},
//            onNavigateToAuthLanding = {},
//            onAnimeClick = {}
//        )
//    }
//}