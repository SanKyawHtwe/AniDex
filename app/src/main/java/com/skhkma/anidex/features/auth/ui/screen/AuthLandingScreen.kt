import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import kotlinx.serialization.Serializable

@Serializable
data object AuthLandingRoute


fun NavController.navigateToAuthLandingScreen(
    navOptions: NavOptions? = null
) {
    navigate(
        route = AuthLandingRoute,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.authLandingScreen(
    onEmailPasswordClick: () -> Unit,
    onEmailPasswordLogin: () -> Unit
) {
    composable<AuthLandingRoute> {
        AuthLandingScreen(
            onEmailPasswordClick = onEmailPasswordClick,
            onEmailPasswordLogin = onEmailPasswordLogin)
    }
}

@Composable
fun AuthLandingScreen(
    modifier: Modifier = Modifier,
    onEmailPasswordClick: () -> Unit,
    onEmailPasswordLogin : () -> Unit
) {
    Scaffold(modifier = modifier) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = onEmailPasswordClick) {
                Text("Email/Password Sign Up")
            }
            Button(
                onClick = onEmailPasswordLogin
            ) {
                Text("Login with email")
            }
        }
    }
}

@Preview
@Composable
private fun AuthLandingScreenPreview() {
    AniDexTheme {
        AuthLandingScreen(onEmailPasswordClick = {},
            onEmailPasswordLogin = {})
    }
}
