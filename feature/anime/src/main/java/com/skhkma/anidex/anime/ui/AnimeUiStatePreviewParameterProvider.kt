package com.skhkma.anidex.anime.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.skhkma.anidex.model.AnimeModel

class AnimeUiStatePreviewParameterProvider : PreviewParameterProvider<TrendingAnimeUiState> {
    override val values: Sequence<TrendingAnimeUiState>
        get() = sequenceOf(
            TrendingAnimeUiState.Success(
                listOf(
                    AnimeModel(
                        id = "69",
                        image = "",
                        title = "Taw thar"
                    )
                )
            ),
            TrendingAnimeUiState.Loading,
            TrendingAnimeUiState.Error("Something went wrong")
        )
}