package com.skhkma.anidex.features.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    val data : List<Anime>
)

@Serializable
data class Anime(
    val id : String,
    val type : String?,
    val attributes : Attributes?
)

@Serializable
data class Attributes(
    val titles : Map<String,String>?,
    val posterImage : PosterImage?
)

@Serializable
data class PosterImage(
    val small : String?,
)


@Serializable
data class Title(
    @SerialName("en")
    val english : String?,
    @SerialName("en_jp")
    val romaji : String?,
    @SerialName("ja_jp")
    val japanese : String?
)

