package com.skhkma.anidex.features.home.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.AniDexTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRoute

fun NavController.navigateToProfileScreen() {
    navigate(ProfileRoute)
}

fun NavGraphBuilder.profileScreen(
    onNavigateToAuthLanding: () -> Unit
) {
    composable<ProfileRoute> {
        ProfileScreen(
            onNavigateToAuthLanding = onNavigateToAuthLanding
        )
    }
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToAuthLanding: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            Firebase.auth.signOut()
            onNavigateToAuthLanding()
        }
    ) {
        Text("Logout")
    }
}


@Preview
@Composable
private fun ProfileScreenPreview() {
    AniDexTheme {
        ProfileScreen(
            onNavigateToAuthLanding = {}
        )
    }
}