package com.skhkma.anidex.data.repository

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel

interface AnimeRepository {
    suspend fun getAnimeList() : Result<List<AnimeModel>>

    suspend fun getCategories(id: String): Result<List<CategoryModel>>

    suspend fun getAnimeDetails(id: String): Result<AnimeDetailModel>

    suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>>
}