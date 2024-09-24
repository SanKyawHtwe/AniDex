package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel
import com.skhkma.anidex.network.mapper.AnimeMapper
import com.skhkma.anidex.network.model.Anime
import com.skhkma.anidex.network.model.AnimeDetailData
import com.skhkma.anidex.network.utils.AnidexApiResponse
import com.skhkma.anidex.network.utils.handle
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

internal class AnimeRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : AnimeRemoteDatasource {

    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return handle<List<Anime>> {
            httpClient.get("https://kitsu.io/api/edge/trending/anime")
        }.map {
            it.map { anime ->
                AnimeMapper.toDomain(anime)
            }
        }
    }

    override suspend fun getCategories(id: String): Result<List<CategoryModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimeDetails(id: String): Result<AnimeDetailModel> {
        return handle<AnimeDetailData> {
            httpClient.get("https://kitsu.io/api/edge/anime/$id") {
                header(HttpHeaders.Accept, "application/vnd.api+json")
            }
        }.map {
            AnimeMapper.toDetailModel(it)
        }
    }

    override suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>> {
        TODO("Not yet implemented")
    }

}