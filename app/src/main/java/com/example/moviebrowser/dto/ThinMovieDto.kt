package com.example.ubbassignment2.dto

import com.example.ubbassignment2.model.ThinMovie
import com.squareup.moshi.JsonClass
import java.util.Locale

@JsonClass(generateAdapter = true)
data class ThinMovieDto(
    val id: Int?,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?,
    val release_date: String?,
    val original_language: String?,
    val genre_ids: List<Int>?
)

fun ThinMovieDto.toThinMovie(): ThinMovie = ThinMovie(
    id = id ?: 0,
    imageUrl = "https://image.tmdb.org/t/p/original".plus(poster_path),
    title = title ?: "Unknown title",
    year = release_date?.substring(0, release_date.indexOf("-")) ?: "Unknown year",
    lang = if (original_language != null)
        Locale(original_language).getDisplayLanguage(Locale.getDefault())
    else "Unknown language",
    rating = vote_average ?: 0.0,
    chips = mutableListOf(),
    genreIds = genre_ids ?: emptyList()
)
