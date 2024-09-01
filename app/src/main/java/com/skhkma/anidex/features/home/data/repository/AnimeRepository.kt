package com.skhkma.anidex.features.home.data.repository

import com.skhkma.anidex.features.home.domain.model.AnimeModel

interface AnimeRepository {
    suspend fun getAnimeList() : Result<List<AnimeModel>>
}