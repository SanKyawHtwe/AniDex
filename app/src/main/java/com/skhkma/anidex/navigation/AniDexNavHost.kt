package com.skhkma.anidex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.skhkma.anidex.anime.ui.AnimeDetailRoute
import com.skhkma.anidex.anime.ui.animeDetailScreen
import com.skhkma.anidex.features.home.ui.screen.HomeRoute
import com.skhkma.anidex.features.home.ui.screen.homeScreen
import com.skhkma.anidex.features.home.ui.screen.navigateToHomeScreen
import com.skhkma.anidex.features.onboarding.navigateToOnboardingScreen
import com.skhkma.anidex.features.onboarding.onboardingScreen


@Composable
fun AniDexNavHost(
    isLoggedIn : Boolean
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AnimeDetailRoute
//        if (isLoggedIn) HomeRoute else AuthNavRoute
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
                        popUpTo<HomeRoute>() {
                            inclusive = true
                        }
                    }
                )
            }
        )
        AuthNav(
            navController = navController
        )
        animeDetailScreen()
    }
}