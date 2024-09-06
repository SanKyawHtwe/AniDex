@file:OptIn(ExperimentalFoundationApi::class)

package com.skhkma.anidex.features.onboarding

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.AniDexTheme
import com.skhkma.anidex.R
import kotlinx.serialization.Serializable

private const val PAGE_COUNT = 3
private const val WEIGHT_EIGHTY_PERCENT = 0.8f
private const val WEIGHT_TWENTY_PERCENT = 0.2f

@Serializable
data object OnboardingRoute

fun NavGraphBuilder.onboardingScreen(
    onNavigateToAuthLanding: () -> Unit
) {
    composable<OnboardingRoute> {
        OnboardingScreen(
            onNavigateToAuthLanding = onNavigateToAuthLanding
        )
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onNavigateToAuthLanding: () -> Unit
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
                modifier = Modifier.weight(WEIGHT_EIGHTY_PERCENT),
            ) { page ->
                when (page) {
                    0 -> OnboardingContent(
                        modifier = Modifier,
                        imageId = R.drawable.onboarding_first_screen_image,
                        headerText = stringResource(R.string.onboarding_header_first),
                        bodyText = stringResource(R.string.onboarding_body_first)
                    )

                    1 -> OnboardingContent(
                        modifier = Modifier,
                        imageId = R.drawable.onboarding_second_screen_image,
                        headerText = stringResource(R.string.onboarding_header_second),
                        bodyText = stringResource(R.string.onboarding_body_second)
                    )

                    2 -> OnboardingContent(
                        modifier = Modifier,
                        imageId = R.drawable.onboarding_third_screen_image,
                        headerText = stringResource(R.string.onboarding_header_third),
                        bodyText = stringResource(R.string.onboarding_body_third)
                    )

                }
            }

            Indicator(
                modifier = Modifier.weight(WEIGHT_TWENTY_PERCENT),
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
                onNavigateToAuthLanding()
            }) {
                Text(text = "Get Started")
            }
        }
    }
}

@Composable
fun OnboardingContent(
    modifier: Modifier = Modifier,
    imageId: Int,
    headerText: String,
    bodyText: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = imageId
            ),
            modifier = Modifier
                .size(300.dp),
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            text = headerText,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Center,
            text = bodyText,
            maxLines = 3,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun OnboardingScreenPreview() {
    AniDexTheme {
        OnboardingScreen(
            onNavigateToAuthLanding = {}
        )
    }
}