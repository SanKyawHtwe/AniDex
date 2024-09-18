package com.skhkma.anidex.model

data class AnimeDetailModel(
    val id: String,
    val title: String,
    val coverImage: String,
    val posterImage: String,
    val averageRating: String,
    val type: String,
    val status: String,
    val startDate: String,
    val ageRating: String,
    val description: String
)
