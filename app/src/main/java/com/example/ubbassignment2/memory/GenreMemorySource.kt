package com.example.ubbassignment2.memory

import com.example.ubbassignment2.model.Genre

class GenreMemorySource {
    private var genres: List<Genre>? = null

    fun cacheGenres(genres: List<Genre>) {
        this.genres = genres
    }

    fun getGenres(): List<Genre>? {
        return genres
    }
}