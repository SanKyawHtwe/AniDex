package com.skhkma.anidex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AnimeResponse(
    val data : List<Anime>
)

@Serializable
internal data class Anime(
    val id : String,
    val type : String?,
    val attributes : Attributes?
)

@Serializable
internal data class Attributes(
    val titles : Map<String,String>?,
    val posterImage : PosterImage?
)

@Serializable
internal data class PosterImage(
    val small : String?,
)


@Serializable
internal data class Title(
    @SerialName("en")
    val english : String?,
    @SerialName("en_jp")
    val romaji : String?,
    @SerialName("ja_jp")
    val japanese : String?
)

