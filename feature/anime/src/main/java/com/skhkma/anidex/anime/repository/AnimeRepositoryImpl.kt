package com.skhkma.anidex.anime.repository

import com.skhkma.anidex.network.datasource.AnimeRemoteDatasource
import com.skhkma.anidex.network.domain.model.AnimeModel

class AnimeRepositoryImpl(private val animeRemoteDatasource: AnimeRemoteDatasource) :
    AnimeRepository {
    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return animeRemoteDatasource.getAnimeList()
    }

}