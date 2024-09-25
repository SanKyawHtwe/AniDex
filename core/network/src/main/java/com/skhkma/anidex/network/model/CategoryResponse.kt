package com.skhkma.anidex.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: String?,
    val attributes: CategoryAttributes?
)

@Serializable
data class CategoryAttributes(
    val title: String?
)
