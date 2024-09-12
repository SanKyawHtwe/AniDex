package com.skhkma.anidex.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import kotlinx.serialization.Serializable
import com.skhkma.anidex.designsystem.R as designR

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
    val scrollState = rememberScrollState(initial = 30)
    Column(
        modifier = modifier
            .height(3000.dp)
            .verticalScroll(state = scrollState)
    ) {
        Row(
            modifier = Modifier
                .height(80.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(80.dp),
                painter = painterResource(
                    designR.drawable.user_profile,
                ),
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("User Name")
                OutlinedButton(
                    modifier = Modifier
                        .heightIn(min = 24.dp),
                    onClick = { },
                    contentPadding = PaddingValues(
                        vertical = 1.dp, horizontal = 16.dp
                    )
                ) {
                    Text("Edit")
                }
            }
        }
        Button(
            modifier = modifier,
            onClick = {
                onNavigateToAuthLanding()
            }
        ) {
            Text("Logout")
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