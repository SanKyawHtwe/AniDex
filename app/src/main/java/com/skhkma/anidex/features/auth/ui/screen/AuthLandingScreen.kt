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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.AniDexTheme
import kotlinx.serialization.Serializable

@Serializable
data object AuthLandingRoute

fun NavGraphBuilder.authLandingScreen(onEmailPasswordClick: () -> Unit) {
    composable<AuthLandingRoute> {
        AuthLandingScreen(onEmailPasswordClick = onEmailPasswordClick)
    }
}

@Composable
fun AuthLandingScreen(modifier: Modifier = Modifier, onEmailPasswordClick: () -> Unit) {
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
        }
    }
}

@Preview
@Composable
private fun AuthLandingScreenPreview() {
    AniDexTheme {
        AuthLandingScreen(onEmailPasswordClick = {})
    }
}