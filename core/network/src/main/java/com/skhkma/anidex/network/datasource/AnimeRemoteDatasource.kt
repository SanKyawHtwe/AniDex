package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.EpisodeModel

interface AnimeRemoteDatasource {
    suspend fun getAnimeList(): Result<List<AnimeModel>>

    suspend fun getAnimeDetails(id: String): Result<AnimeDetailModel>

    suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>>
}

