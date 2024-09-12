package com.skhkma.anidex.network.mapper

import com.skhkma.anidex.network.model.AnimeResponse
import com.skhkma.anidex.model.AnimeModel

internal object AnimeMapper {
    fun toDomain(animeResponse: AnimeResponse): List<com.skhkma.anidex.model.AnimeModel> {
        return animeResponse.data.map {
            com.skhkma.anidex.model.AnimeModel(
                id = it.id,
                title = it.attributes?.titles?.values?.first().orEmpty(),
                image = it.attributes?.posterImage?.small.orEmpty()
            )
        }
    }
}