package com.skhkma.anidex.navigation

import AuthLandingRoute
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import authLandingScreen
import com.skhkma.anidex.features.auth.ui.screen.emailPasswordSignUpScreen
import com.skhkma.anidex.features.auth.ui.screen.navigateToEmailPasswordSignUpScreen
import com.skhkma.anidex.features.home.ui.screen.homeScreen
import com.skhkma.anidex.features.home.ui.screen.navigateToHomeScreen
import com.skhkma.anidex.features.onboarding.navigateToOnboardingScreen
import com.skhkma.anidex.features.onboarding.onboardingScreen


@Composable
fun AniDexNavHost() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthLandingRoute
    ) {
        onboardingScreen(
            onNavigateToHome = {
                navController.navigateToHomeScreen()
            }
        )
        homeScreen(
            onNavigateToManga = {
                navController.navigateToOnboardingScreen()
            }
        )
        authLandingScreen(
            onEmailPasswordClick = {
                navController.navigateToEmailPasswordSignUpScreen()
            }
        )
        emailPasswordSignUpScreen(
            onNavigateToHome = {
                navController.navigateToHomeScreen()
            }
        )
    }
}