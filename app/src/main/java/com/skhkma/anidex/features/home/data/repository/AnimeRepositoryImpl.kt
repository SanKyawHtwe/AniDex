package com.skhkma.anidex.features.home.data.repository

import com.skhkma.anidex.features.home.data.datasource.AnimeRemoteDataSourceImpl
import com.skhkma.anidex.features.home.data.datasource.AnimeRemoteDatasource
import com.skhkma.anidex.features.home.domain.model.AnimeModel

class AnimeRepositoryImpl(private val animeRemoteDatasource: AnimeRemoteDatasource) : AnimeRepository{
    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return animeRemoteDatasource.getAnimeList()
    }

}