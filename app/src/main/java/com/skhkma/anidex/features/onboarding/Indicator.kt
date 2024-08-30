package com.skhkma.anidex.features.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Indicator(modifier: Modifier, count: Int, currentIndex: Int) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { i ->
            val scale = animateFloatAsState(
                targetValue = if (currentIndex == i) 1.2f else 1f,
                label = "scale animation"
            )
            val color = animateColorAsState(
                targetValue = if (i == currentIndex) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },
                label = "color animation"
            )
            Box(
                modifier = Modifier
                    .size(size = 10.dp)
                    .scale(scale.value)
                    .clip(CircleShape)
                    .background(color = color.value)
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Preview()
@Composable
private fun IndicatorPreview() {
    Indicator(modifier = Modifier, count = 3, currentIndex = 1)
}