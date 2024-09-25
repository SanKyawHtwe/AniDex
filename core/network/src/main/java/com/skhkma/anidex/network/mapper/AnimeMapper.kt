package com.skhkma.anidex.network.mapper

import com.skhkma.anidex.model.AnimeDetailModel
import com.skhkma.anidex.model.AnimeModel
import com.skhkma.anidex.model.Status
import com.skhkma.anidex.network.model.Anime
import com.skhkma.anidex.network.model.AnimeDetailData

internal object AnimeMapper {
    fun toDomain(anime: Anime): AnimeModel {
        return AnimeModel(
            id = anime.id,
            title = anime.attributes?.titles?.values?.first().orEmpty(),
            image = anime.attributes?.posterImage?.small.orEmpty()
        )
    }

    fun toDetailModel(detail: AnimeDetailData): AnimeDetailModel {
        return detail.let {
            AnimeDetailModel(
                id = it.id.orEmpty(),
                title = it.attributes?.titles?.values?.first().orEmpty(),
                coverImage = it.attributes?.coverImage?.original.orEmpty(),
                posterImage = it.attributes?.posterImage?.small.orEmpty(),
                averageRating = it.attributes?.averageRating.orEmpty(),
                type = it.attributes?.subtype.orEmpty(),
                status = it.attributes?.status?.let { status -> toEnumStatus(status) },
                startDate = it.attributes?.startDate.orEmpty(),
                ageRating = it.attributes?.ageRating.orEmpty(),
                description = it.attributes?.description.orEmpty()
            )
        }
    }



    private fun toEnumStatus(status: String): Status {
        return when (status) {
            "current" -> Status.CURRENT
            "finished" -> Status.FINISHED
            "tba" -> Status.TO_BE_ANNOUNCED
            "unreleased" -> Status.UNRELEASED
            "upcoming" -> Status.UPCOMING
            else -> Status.TO_BE_ANNOUNCED
        }
    }

}