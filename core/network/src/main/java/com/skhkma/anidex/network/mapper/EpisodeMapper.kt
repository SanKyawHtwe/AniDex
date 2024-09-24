package com.skhkma.anidex.network.mapper

import com.skhkma.anidex.model.EpisodeModel
import com.skhkma.anidex.network.model.EpisodeResponse

object EpisodeMapper {

    fun toDomain(episode: EpisodeResponse): EpisodeModel {
        return EpisodeModel(
            id = episode.id.orEmpty(),
            thumbnail = episode.attributes?.thumbnail?.original.orEmpty(),
            number = episode.attributes?.number ?: -1,
            title = episode.attributes?.canonicalTitle.orEmpty()
        )
    }

}