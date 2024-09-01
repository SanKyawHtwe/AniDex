package com.skhkma.anidex.features.home.data.mapper

import com.skhkma.anidex.features.home.data.model.AnimeResponse
import com.skhkma.anidex.features.home.domain.model.AnimeModel

internal object AnimeMapper {
    fun toDomain(animeResponse: AnimeResponse): List<AnimeModel> {
        return animeResponse.data.map {
            AnimeModel(
                id = it.id,
                title = it.attributes?.titles?.values?.first().orEmpty(),
                image = it.attributes?.posterImage?.small.orEmpty()
            )
        }
    }
}