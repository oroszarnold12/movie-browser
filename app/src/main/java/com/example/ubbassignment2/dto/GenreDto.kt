package com.example.ubbassignment2.dto

import com.example.ubbassignment2.model.Genre
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDto(
    val id: Int?,
    val name: String?
)

fun GenreDto.toGenre(): Genre = Genre(
    id = id ?: 0,
    name = name ?: "Unknown"
)
