@file:OptIn(ExperimentalFoundationApi::class)

package com.skhkma.anidex.features.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.AniDexTheme
import com.skhkma.anidex.R
import com.skhkma.anidex.features.home.HomeRoute
import kotlinx.serialization.Serializable

private const val PAGE_COUNT = 3

@Serializable
data object OnboardingRoute


fun NavGraphBuilder.onboardingScreen(
    onNavigateToHome: () -> Unit
) {
    composable<OnboardingRoute> {
        OnboardingScreen(
            onNavigateToHome = onNavigateToHome
        )
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {
        PAGE_COUNT
    })
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(0.8f),
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.onboarding_image1
                        ),
                        modifier = Modifier
                            .size(300.dp)
                            .clip(shape = RoundedCornerShape(18.dp)),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Onboarding Image",
                    )
                    Text(
                        text = stringResource(R.string.onboarding_header),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = stringResource(R.string.onboarding_body),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Indicator(
                modifier = Modifier.weight(0.2f),
                count = PAGE_COUNT,
                currentIndex = pagerState.currentPage
            )
        }
        AnimatedVisibility(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter),
            enter = fadeIn(),
            exit = fadeOut(),
            visible = pagerState.currentPage == PAGE_COUNT - 1
        ) {
            Button(onClick = {
                onNavigateToHome()
            }) {
                Text(text = "Get Started")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    AniDexTheme {
        OnboardingScreen(
            onNavigateToHome = {}
        )
    }
}