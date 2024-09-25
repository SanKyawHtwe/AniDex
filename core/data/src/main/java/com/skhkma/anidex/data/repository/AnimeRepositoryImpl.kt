package com.skhkma.anidex.data.repository

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel
import com.skhkma.anidex.network.datasource.AnimeRemoteDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


internal class AnimeRepositoryImpl(
    private val animeRemoteDatasource: AnimeRemoteDatasource
) : AnimeRepository {

    private var cachedAnimeMap: Map<String, AnimeModel> = emptyMap()

    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return animeRemoteDatasource.getAnimeList().also {
            it.onSuccess { value ->
                cachedAnimeMap = value.associateBy(
                    { each -> each.id },
                    { each -> each }
                )
            }
        }
    }

    override suspend fun getCategories(id: String): Result<List<CategoryModel>> {
        return animeRemoteDatasource.getCategories(id = id)
    }

    override suspend fun getAnimeDetails(id: String): Flow<Result<AnimeDetailModel>> {
        val cachedAnime = cachedAnimeMap[id]
        return flow {
            emit(
                Result.success(
                    AnimeDetailModel(
                        id = cachedAnime?.id.orEmpty(),
                        title = cachedAnime?.title.orEmpty(),
                        coverImage = "",
                        posterImage = cachedAnime?.image.orEmpty(),
                        averageRating = "",
                        type = "",
                        status = null,
                        startDate = "",
                        ageRating = "",
                        description = ""
                    )
                )
            )
            emit(animeRemoteDatasource.getAnimeDetails(id))
        }
    }

    override suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>> {
        return animeRemoteDatasource.getEpisodesByAnimeId(animeId)
    }
}