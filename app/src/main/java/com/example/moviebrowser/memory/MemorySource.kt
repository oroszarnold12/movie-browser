package com.example.ubbassignment2.memory

object MemorySource {
    val movieMemorySource: MovieMemorySource by lazy {
        MovieMemorySource()
    }

    val genreMemorySource: GenreMemorySource by lazy {
        GenreMemorySource()
    }
}