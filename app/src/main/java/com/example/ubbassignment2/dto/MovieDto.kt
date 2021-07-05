package com.example.ubbassignment2.dto

import com.example.ubbassignment2.model.Movie
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class MovieDto(
    val id: Int?,
    val title: String?,
    val poster_path: String?,
    val overview: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val release_date: String?,
    val original_language: String?,
    val genres: List<GenreDto>?,
    val runtime: Int?
)

fun MovieDto.toMovie(): Movie = Movie(
    id = id ?: 0,
    imageUrl = "https://image.tmdb.org/t/p/original".plus(poster_path),
    title = title ?: "Unknown title",
    year = release_date?.substring(0, release_date.indexOf("-")) ?: "Unknown year",
    pg = "PG-13",
    time = "".plus(runtime?.div(60) ?: "Unknown")
        .plus("h ")
        .plus(runtime?.rem(60) ?: "unknown")
        .plus("min"),
    lang = if (original_language != null)
        Locale(original_language).getDisplayLanguage(Locale.getDefault())
    else "Unknown language",
    description = overview ?: "No description yet",
    chips = genres?.map { it -> it.name }.orEmpty(),
    tomatoPercentage = "64%",
    rating = vote_average ?: 0.0,
    numOfRating = vote_count?.toString() ?: "Unknown nr of votes"
)