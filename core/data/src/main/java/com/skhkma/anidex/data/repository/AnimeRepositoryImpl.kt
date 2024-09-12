package com.skhkma.anidex.data.repository

import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.network.datasource.AnimeRemoteDatasource


class AnimeRepositoryImpl(private val animeRemoteDatasource: AnimeRemoteDatasource) :
    AnimeRepository {
    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return animeRemoteDatasource.getAnimeList()
    }

}