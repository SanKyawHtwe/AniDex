package com.skhkma.anidex.features.home.data.datasource

import com.skhkma.anidex.features.home.data.mapper.AnimeMapper
import com.skhkma.anidex.features.home.data.model.AnimeResponse
import com.skhkma.anidex.features.home.data.utils.handle
import com.skhkma.anidex.features.home.domain.model.AnimeModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class AnimeRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : AnimeRemoteDatasource {
    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return handle<AnimeResponse> {
            httpClient.get("https://kitsu.io/api/edge/trending/anime")
        }.map {
            AnimeMapper.toDomain(
                animeResponse = it
            )
        }
    }
}