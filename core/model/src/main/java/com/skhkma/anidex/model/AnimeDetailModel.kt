package com.skhkma.anidex.model

data class AnimeDetailModel(
    val id: String,
    val title: String,
    val coverImage: String,
    val posterImage: String,
    val averageRating: String,
    val type: String,
    val status: Status?,
    val startDate: String,
    val ageRating: String,
    val description: String
)

enum class Status(
    val value: String
){
    CURRENT("current"),
    FINISHED("finished"),
    TO_BE_ANNOUNCED("tba"),
    UNRELEASED("unreleased"),
    UPCOMING("upcoming")
}
