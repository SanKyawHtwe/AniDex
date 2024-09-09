package com.skhkma.anidex.features.home.data.repository

import com.skhkma.anidex.network.domain.model.AnimeModel

interface AnimeRepository {
    suspend fun getAnimeList() : Result<List<AnimeModel>>
}