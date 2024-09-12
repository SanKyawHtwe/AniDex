package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.model.AnimeModel

interface AnimeRemoteDatasource {
    suspend fun getAnimeList() : Result<List<com.skhkma.anidex.model.AnimeModel>>
}