package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel
import com.skhkma.anidex.network.mapper.AnimeMapper
import com.skhkma.anidex.network.model.AnimeResponse
import com.skhkma.anidex.network.utils.handle
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class AnimeRemoteDataSourceImpl(
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

    override suspend fun getCategories(id: String): Result<List<CategoryModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimeDetails(id: String): Result<AnimeDetailModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>> {
        TODO("Not yet implemented")
    }
}