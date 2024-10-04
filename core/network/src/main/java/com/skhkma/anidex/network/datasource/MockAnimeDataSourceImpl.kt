package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.CategoryModel
import com.skhkma.anidex.model.EpisodeModel
import com.skhkma.anidex.model.Status

class MockAnimeDataSourceImpl : AnimeRemoteDatasource {
    override suspend fun getAnimeList(offset: Int): List<AnimeModel> {
        return emptyList()
    }

    override suspend fun getCategories(id: String): Result<List<CategoryModel>> {
        return Result.success(
            listOf(
                CategoryModel(
                    id = "0",
                    title = "Comedy"
                ),
                CategoryModel(
                    id = "1",
                    title = "Action"
                ),
                CategoryModel(
                    id = "2",
                    title = "Adventure"
                ),
                CategoryModel(
                    id = "3",
                    title = "Drama"
                ),
                CategoryModel(
                    id = "4",
                    title = "Magic"
                ),
                CategoryModel(
                    id = "5",
                    title = "Romance"
                ),
                CategoryModel(
                    id = "6",
                    title = "Fantasy"
                ),
                CategoryModel(
                    id = "7",
                    title = "Mystery"
                ),
                CategoryModel(
                    id = "8",
                    title = "Slice of Life"
                ),
            )
        )
    }

    override suspend fun getAnimeDetails(id: String): Result<AnimeDetailModel> {
        return Result.success(
            AnimeDetailModel(
                id = "0",
                title = "Cowboy Bebop",
                coverImage = "https://images.alphacoders.com/136/1361559.jpeg",
                posterImage =
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6F_0YOA3QEJIjPoJAS_gUMv6_N5X-Dt_fLw&s",
                averageRating = "88.99%",
                type = "TV",
                status = Status.FINISHED,
                startDate = "1998-04-03",
                ageRating = "R",
                description = "The journey to the martial peak is a lonely, solitary and long one.In the face of adversity,you must survive and remain unyielding.Only then can you break through and and continue on your journey to become the strongest. Sky Tower tests its disciples in the harshest ways to prepare them for this journey.One day the lowly sweeper Yang Kai managed to obtain a black book, setting him on the road to the peak of the martials world.(Source: MU)"
            )
        )
    }

    override suspend fun getEpisodesByAnimeId(animeId: String): Result<List<EpisodeModel>> {
        return Result.success(
            listOf(
                EpisodeModel(
                    id = "0",
                    thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
                    number = 1,
                    title = "Dummy episode title one"
                ),
                EpisodeModel(
                    id = "1",
                    thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
                    number = 2,
                    title = "Dummy episode title two"
                ),
                EpisodeModel(
                    id = "2",
                    thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
                    number = 3,
                    title = "Dummy episode title three"
                ),
                EpisodeModel(
                    id = "3",
                    thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
                    number = 4,
                    title = "Dummy episode title four"
                ),
                EpisodeModel(
                    id = "4",
                    thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
                    number = 5,
                    title = "Dummy episode title five"
                ),
                EpisodeModel(
                    id = "5",
                    thumbnail = "https://images.alphacoders.com/136/1361559.jpeg",
                    number = 6,
                    title = "Dummy episode title six"
                ),
            )
        )
    }
}