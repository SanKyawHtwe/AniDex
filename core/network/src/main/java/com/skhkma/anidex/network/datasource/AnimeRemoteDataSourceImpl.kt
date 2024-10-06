package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel
import com.skhkma.anidex.network.mapper.AnimeMapper
import com.skhkma.anidex.network.mapper.CategoryMapper
import com.skhkma.anidex.network.mapper.EpisodeMapper
import com.skhkma.anidex.network.model.Anime
import com.skhkma.anidex.network.model.AnimeDetailData
import com.skhkma.anidex.network.model.CategoryResponse
import com.skhkma.anidex.network.model.EpisodeResponse
import com.skhkma.anidex.network.model.PagingResponse
import com.skhkma.anidex.network.utils.handle
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.parameters

internal class AnimeRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : AnimeRemoteDatasource {

    override suspend fun getAnimeList(offset: Int): List<AnimeModel> {
        val response: PagingResponse<Anime> = httpClient.get("https://kitsu.io/api/edge/anime") {
                header(HttpHeaders.Accept, "application/vnd.api+json")
                url {
                    parameters.append("page[limit]", "10")
                    parameters.append("page[offset]", "$offset")
                }
            }.body()
        return response.data.map { AnimeMapper.toDomain(it) }
    }

    override suspend fun getCategories(id: String): Result<List<CategoryModel>> {
        return handle<List<CategoryResponse>> {
            httpClient.get("https://kitsu.io/api/edge/anime/$id/categories") {
                header(HttpHeaders.Accept, "application/vnd.api+json")
            }
        }.map {
            it.map { category -> CategoryMapper.toDomain(category) }
        }
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
        return handle<List<EpisodeResponse>> {
            httpClient.get("https://kitsu.io/api/edge/anime/$animeId/episodes") {
                header(HttpHeaders.Accept, "application/vnd.api+json")
            }
        }.map {
            it.map { episode -> EpisodeMapper.toDomain(episode) }
        }
    }

}