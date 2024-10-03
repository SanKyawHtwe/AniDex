package com.skhkma.anidex.home.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeChild

@Composable
fun FloatingNavigationBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        containerColor = Color.Transparent,
        modifier = modifier
            .padding(24.dp)
            .hazeChild(
                state = hazeState,
                shape = RoundedCornerShape(24.dp),
                style = HazeStyle(
                    blurRadius = 12.dp
                )
            ),
        windowInsets = WindowInsets(0, 0, 0, 0),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun FloatingNavigationBarPreview() {
    AniDexTheme {
        FloatingNavigationBar(
            hazeState = HazeState(),
            content = {
                repeat(4) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = ""
                            )
                        },
                        label = { Text("Home") },
                        selected = it == 0,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = Color.Gray
                        ),
                        onClick = {}
                    )
                }
            }
        )
    }
}