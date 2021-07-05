package com.example.ubbassignment2.model

data class Movie(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val year: String,
    val pg: String,
    val time: String,
    val lang: String,
    val description: String,
    val chips: List<String?>,
    val tomatoPercentage: String,
    val rating: Double,
    val numOfRating: String
)
