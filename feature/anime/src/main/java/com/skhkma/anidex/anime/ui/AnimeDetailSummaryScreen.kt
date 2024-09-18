package com.skhkma.anidex.anime.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.skhkma.anidex.designsystem.R
import com.skhkma.anidex.model.AnimeDetailModel
import kotlinx.serialization.Serializable

@Serializable
data object AnimeDetailSummaryRoute

@Composable
fun AnimeDetailSummaryScreen(modifier: Modifier = Modifier, anime: AnimeDetailModel) {
    Column(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxSize(),
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
                    .padding(start = 20.dp),
                model = anime.posterImage,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.place_holder_image),
            )
            Column(
                modifier = Modifier.padding(
                    vertical = 12.dp,
                    horizontal = 20.dp
                )
            ) {
                Text(
                    text = "Community rating : ${anime.averageRating}",
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Type : ${anime.type}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Status : ${anime.status}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Aired : ${anime.startDate}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Age rating : ${anime.ageRating}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            text = anime.description,
            overflow = TextOverflow.Ellipsis
        )
    }
}