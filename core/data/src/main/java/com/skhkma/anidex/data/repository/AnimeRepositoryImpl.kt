package com.skhkma.anidex.data.repository

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.EpisodeModel
import com.skhkma.anidex.network.datasource.AnimeRemoteDatasource


internal class AnimeRepositoryImpl(
    private val animeRemoteDatasource: AnimeRemoteDatasource
) : AnimeRepository {
    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return animeRemoteDatasource.getAnimeList()
    }

    override suspend fun getAnimeDetails(id: String): Result<AnimeDetailModel> {
        return animeRemoteDatasource.getAnimeDetails(id)
    }

    override suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>> {
        return animeRemoteDatasource.getEpisodesByAnimeId(animeId)
    }
}