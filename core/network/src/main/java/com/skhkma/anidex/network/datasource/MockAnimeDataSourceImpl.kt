package com.skhkma.anidex.network.datasource

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.EpisodeModel

class MockAnimeDataSourceImpl : AnimeRemoteDatasource {
    override suspend fun getAnimeList(): Result<List<AnimeModel>> {
        return Result.success(emptyList())
    }

    override suspend fun getAnimeDetails(id: String): Result<AnimeDetailModel> {
        return Result.success(
            AnimeDetailModel(
                id = "0",
                title = "Cowboy Bebop",
                coverImage = "https://images.alphacoders.com/136/1361559.jpeg",
                posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6F_0YOA3QEJIjPoJAS_gUMv6_N5X-Dt_fLw&s",
                averageRating = "88.99%",
                type = "TV",
                status = "Finished",
                startDate = "1998-04-03",
                ageRating = "R",
                description = "In the year 2071, humanity has colonoized several of the planets and moons..."
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