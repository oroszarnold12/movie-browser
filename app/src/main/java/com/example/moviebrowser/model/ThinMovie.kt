package com.example.ubbassignment2.model

data class ThinMovie(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val year: String,
    val lang: String,
    val genreIds: List<Int>,
    val chips: MutableList<String>,
    val rating: Double,
)
