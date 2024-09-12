package com.skhkma.anidex.navigation

import AuthLandingRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions

import authLandingScreen
import com.skhkma.anidex.features.auth.ui.login.screen.emailPasswordLoginScreen
import com.skhkma.anidex.features.auth.ui.login.screen.navigateToEmailPasswordLoginScreen
import com.skhkma.anidex.features.auth.ui.signup.screen.emailPasswordSignUpScreen
import com.skhkma.anidex.features.auth.ui.signup.screen.navigateToEmailPasswordSignUpScreen
import com.skhkma.anidex.features.home.ui.screen.navigateToHomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object AuthNavRoute


fun NavController.navigateToAuthNavGraph(
    navOptions: NavOptions? = null
) {
    navigate(
        AuthNavRoute,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.AuthNav(
    navController: NavHostController
) {
    navigation<AuthNavRoute>(
        startDestination = AuthLandingRoute
    ) {
        authLandingScreen(onEmailPasswordClick = {
            navController.navigateToEmailPasswordSignUpScreen()
        }, onEmailPasswordLogin = {
            navController.navigateToEmailPasswordLoginScreen()
        })
        emailPasswordSignUpScreen(onNavigateToHome = {
            navController.navigateToHomeScreenAndClearBackStack()
        })
        emailPasswordLoginScreen(
            onNavigateToHome = {
                navController.navigateToHomeScreenAndClearBackStack()
            }
        )
    }

}

private fun NavController.navigateToHomeScreenAndClearBackStack() {
    navigateToHomeScreen(navOptions = navOptions {
        popUpTo<AuthNavRoute> {
            inclusive = true
        }
    })
}

