package com.skhkma.anidex.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class PagingResponse<T>(
    val data: List<T>,
    val meta: Meta
)

@Serializable
internal data class Meta(
    val count: Int
)

@Serializable
internal data class Anime(
    val id: String,
    val type: String?,
    val attributes: Attributes?
)

@Serializable
internal data class Attributes(
    val titles: Map<String, String>?,
    val posterImage: PosterImage?,
    val description: String?,
    val canonicalTitle: String?,
    val averageRating: String?,
    val startDate: String?,
    val ageRating: String?,
    val status: String?,
    val coverImage: CoverImage?,
    val subtype: String?
)

@Serializable
internal data class CoverImage(
    val original: String?
)

@Serializable
internal data class PosterImage(
    val small: String?,
)

