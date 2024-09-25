package com.skhkma.anidex.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.skhkma.anidex.anime.ui.animeDetailScreen
import com.skhkma.anidex.anime.ui.navigateToAnimeDetail
import com.skhkma.anidex.home.ui.screen.HomeRoute
import com.skhkma.anidex.home.ui.screen.homeScreen
import com.skhkma.anidex.home.ui.screen.navigateToHomeScreen
import com.skhkma.anidex.features.onboarding.navigateToOnboardingScreen
import com.skhkma.anidex.features.onboarding.onboardingScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AniDexNavHost(
    isLoggedIn: Boolean
) {
    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) HomeRoute else AuthNavRoute
        ) {
            onboardingScreen(
                onNavigateToAuthLanding = {
                    navController.navigateToHomeScreen()
                }
            )
            homeScreen(
                onNavigateToManga = {
                    navController.navigateToOnboardingScreen()
                },
                onNavigateToAuthLanding = {
                    navController.navigateToAuthNavGraph(
                        navOptions = navOptions {
                            popUpTo<HomeRoute> {
                                inclusive = true
                            }
                        }
                    )
                },
                onAnimeClick = {
                    navController.navigateToAnimeDetail(it)
                },
                sharedTransitionScope = this@SharedTransitionLayout,
            )
            AuthNav(
                navController = navController
            )
            animeDetailScreen(
                onNavigateUp = navController::navigateUp,
                sharedTransitionScope = this@SharedTransitionLayout
            )
        }
    }
}
