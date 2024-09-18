package com.skhkma.anidex.anime.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.skhkma.anidex.designsystem.R
import kotlinx.serialization.Serializable

@Serializable
data object AnimeDetailSummaryRoute

fun NavGraphBuilder.animeDetailSummaryScreen(){
    composable<AnimeDetailSummaryRoute> {
        AnimeDetailSummaryScreen()
    }
}

@Composable
fun AnimeDetailSummaryScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(top = 8.dp)) {
        Row {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
                    .padding(start = 20.dp),
                painter = painterResource(R.drawable.anime_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.padding(
                    vertical = 12.dp,
                    horizontal = 20.dp
                )
            ) {
                Text(
                    text = "Community rating : 89.9%",
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Type : TV",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Status : Finished",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Aired : Jan 8, 1996",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Age rating : PG-13",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            text = stringResource(com.skhkma.anidex.anime.R.string.anime_description),
            overflow = TextOverflow.Ellipsis
        )
    }
}