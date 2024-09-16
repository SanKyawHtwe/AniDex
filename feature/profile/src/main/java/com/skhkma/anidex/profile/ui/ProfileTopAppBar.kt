package com.skhkma.anidex.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skhkma.anidex.designsystem.R
import com.skhkma.anidex.designsystem.theme.AniDexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior?
) {
    LargeTopAppBar(
        modifier = modifier,
        title = {
            Box(
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(R.drawable.place_holder_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                UserProfile()
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun UserProfile(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(80.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(80.dp),
            painter = painterResource(
                R.drawable.user_profile,
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ProfileTopAppBarPreview() {
    AniDexTheme {
        ProfileTopAppBar(
            scrollBehavior = null
        )
    }
}