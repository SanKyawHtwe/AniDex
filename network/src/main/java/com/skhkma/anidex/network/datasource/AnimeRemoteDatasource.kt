package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.network.domain.model.AnimeModel

interface AnimeRemoteDatasource {
    suspend fun getAnimeList() : Result<List<AnimeModel>>
}