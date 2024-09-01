package com.skhkma.anidex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skhkma.anidex.features.home.AnimeRoute
import com.skhkma.anidex.features.home.AnimeScreen
import com.skhkma.anidex.features.home.HomeRoute
import com.skhkma.anidex.features.home.homeScreen
import com.skhkma.anidex.features.onboarding.OnboardingRoute
import com.skhkma.anidex.features.onboarding.onboardingScreen


@Composable
fun AniDexNavHost() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        onboardingScreen(
            onNavigateToHome = {
                navController.navigate(HomeRoute)
            }
        )
        homeScreen(
            onNavigateToManga = {
                navController.navigate(OnboardingRoute)
            }
        )
    }
}