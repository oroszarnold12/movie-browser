package com.example.ubbassignment2.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreListDto(
    val genres: List<GenreDto>
)
