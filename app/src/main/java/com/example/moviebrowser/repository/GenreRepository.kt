package com.example.ubbassignment2.repository

import com.example.ubbassignment2.dto.toGenre
import com.example.ubbassignment2.memory.GenreMemorySource
import com.example.ubbassignment2.memory.MemorySource
import com.example.ubbassignment2.model.Genre
import com.example.ubbassignment2.service.GenreService
import com.example.ubbassignment2.service.ServiceApi
import com.example.ubbassignment2.service.ServiceException

class GenreRepository {
    private val genreService: GenreService = ServiceApi.genreService
    private val genreMemorySource: GenreMemorySource = MemorySource.genreMemorySource

    suspend fun getGenres(): List<Genre> {
        try {
            return genreMemorySource.getGenres()
                ?: genreService.getGenres().genres.map { it.toGenre() }
                    .also {
                        genreMemorySource.cacheGenres(it)
                    }

        } catch (exception: Exception) {
            throw ServiceException(exception.message, exception)
        }
    }
}