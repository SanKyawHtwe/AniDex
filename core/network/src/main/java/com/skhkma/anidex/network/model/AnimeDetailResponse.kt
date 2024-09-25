package com.skhkma.anidex.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class AnimeDetailData(
    val id: String?,
    val type: String?,
    val attributes: Attributes?,
)
