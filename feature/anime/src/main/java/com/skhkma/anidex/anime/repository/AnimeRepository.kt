package com.skhkma.anidex.anime.repository

import com.skhkma.anidex.network.domain.model.AnimeModel

interface AnimeRepository {
    suspend fun getAnimeList() : Result<List<AnimeModel>>
}