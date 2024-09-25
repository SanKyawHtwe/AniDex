package com.skhkma.anidex.data.repository

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeList() : Result<List<AnimeModel>>

    suspend fun getCategories(id: String): Result<List<CategoryModel>>

    suspend fun getAnimeDetails(id: String): Flow<Result<AnimeDetailModel>>

    suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>>
}