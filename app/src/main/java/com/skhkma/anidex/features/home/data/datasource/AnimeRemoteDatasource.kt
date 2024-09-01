package com.skhkma.anidex.features.home.data.datasource

import com.skhkma.anidex.features.home.domain.model.AnimeModel

interface AnimeRemoteDatasource {
    suspend fun getAnimeList() : Result<List<AnimeModel>>
}