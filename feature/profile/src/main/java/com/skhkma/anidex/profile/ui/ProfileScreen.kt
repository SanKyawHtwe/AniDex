package com.skhkma.anidex.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skhkma.anidex.designsystem.R
import com.skhkma.anidex.designsystem.theme.AniDexTheme
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

private data class ProfileTabRoute<T : Any>(val name: String, val route: T)

private val profileTabRoute = listOf(
    ProfileTabRoute("Favourite Animes", FavouriteAnimesRoute),
    ProfileTabRoute("Favourite Mangas", FavouriteMangasRoute)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    onNavigateToAuthLanding: () -> Unit
) {
    val navController = rememberNavController()
    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        Box(
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(R.drawable.place_holder_image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            UserProfile(
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )
        }
        var tabState by remember { mutableIntStateOf(0) }

        SecondaryTabRow(
            selectedTabIndex = tabState,
        ) {
            profileTabRoute.forEachIndexed { index, profileTabRoute ->
                Tab(
                    onClick = {
                        tabState = index
                        navController.navigate(profileTabRoute.route)
                    },
                    selected = tabState == index,
                    text = {
                        Text(
                            text = profileTabRoute.name, maxLines = 1, overflow = TextOverflow.Ellipsis
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                )
            }

        }
        NavHost(
            modifier = Modifier.fillMaxWidth(),
            navController = navController,
            startDestination = FavouriteAnimesRoute,
        ) {
            favouriteAnimesScreen()
            favouriteMangasScreen()
        }
    }
}



@Preview(showBackground = true, fontScale = 1.0f)
@Composable
private fun ProfileScreenPreview() {
    AniDexTheme {
        ProfileScreen(
            onNavigateToAuthLanding = {}
        )
    }
}