package com.skhkma.anidex.data.repository

import com.skhkma.anidex.model.AnimeModel

interface AnimeRepository {
    suspend fun getAnimeList() : Result<List<AnimeModel>>
}