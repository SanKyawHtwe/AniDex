package com.skhkma.anidex.anime.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skhkma.anidex.designsystem.R
import com.skhkma.anidex.designsystem.theme.AniDexTheme
import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.Status
import kotlinx.serialization.Serializable

@Serializable
data object AnimeDetailSummaryRoute

@OptIn(ExperimentalLayoutApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun AnimeDetailSummaryScreen(
    modifier: Modifier = Modifier,
    anime: AnimeDetailModel,
    categoryUiState: AnimeCategoryUiState,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    Column(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxSize(),
    ) {
        Row {
            with(sharedTransitionScope) {
                AsyncImage(
                    modifier = Modifier
                        .sharedElement(
                            sharedTransitionScope.rememberSharedContentState(key = "image-${anime.id}"),
                            animatedVisibilityScope = animatedContentScope
                        )
                        .width(150.dp)
                        .height(200.dp)
                        .padding(start = 20.dp),
                    model = anime.posterImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.place_holder_image),
                )
            }
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
                    text = "Status : ${anime.status?.value}",
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

        if (categoryUiState is AnimeCategoryUiState.Success) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(categoryUiState.categories.size) { item ->
                    SuggestionChip(
                        onClick = {},
                        label = { Text(text = categoryUiState.categories[item].title) }
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = anime.description,
            maxLines = 7,
            overflow = TextOverflow.Ellipsis
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun SummaryPreview() {
//    AniDexTheme {
//        AnimeDetailSummaryScreen(
//            anime = AnimeDetailModel(
//                id = "0",
//                title = "Cowboy Bebop",
//                coverImage = "https://images.alphacoders.com/136/1361559.jpeg",
//                posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6F_0YOA3QEJIjPoJAS_gUMv6_N5X-Dt_fLw&s",
//                averageRating = "88.99%",
//                type = "TV",
//                status = Status.FINISHED,
//                startDate = "1998-04-03",
//                ageRating = "R",
//                description = "In the year 2071, humanity has colonized several of the planets and moons..."
//            ),
//            categoryUiState = AnimeCategoryUiState.Success(
//                listOf(
//                    CategoryModel(
//                        id = "0",
//                        title = "Comedy"
//                    ),
//                    CategoryModel(
//                        id = "0",
//                        title = "Comedy"
//                    ),
//                    CategoryModel(
//                        id = "0",
//                        title = "Comedy"
//                    ),
//                )
//            )
//        )
//    }
//}