package com.skhkma.anidex.network.model

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: String?,
    val attributes: EpisodeAttributes?
)

@Serializable
data class EpisodeAttributes(
    val canonicalTitle: String?,
    val number: Int?,
    val thumbnail: ThumbnailResponse?
)

@Serializable
data class ThumbnailResponse(
    val original: String?
)
