package com.example.ubbassignment2.repository

import com.example.ubbassignment2.dto.toMovie
import com.example.ubbassignment2.dto.toThinMovie
import com.example.ubbassignment2.memory.MemorySource
import com.example.ubbassignment2.memory.MovieMemorySource
import com.example.ubbassignment2.model.Movie
import com.example.ubbassignment2.model.ThinMovie
import com.example.ubbassignment2.service.MovieService
import com.example.ubbassignment2.service.ServiceApi
import com.example.ubbassignment2.service.ServiceException

class MovieRepository {
    private val movieService: MovieService = ServiceApi.movieService
    private val movieMemorySource: MovieMemorySource = MemorySource.movieMemorySource

    suspend fun getMovie(id: Int): Movie {
        try {
            return movieMemorySource.getMovie(id) ?: movieService.getMovie(id).toMovie()
                .also {
                    movieMemorySource.cacheMovie(it)
                }

        } catch (exception: Exception) {
            throw ServiceException(exception.message, exception)
        }
    }

    suspend fun getMovies(): List<ThinMovie> {
        try {
            return movieMemorySource.getMovies()
                ?: movieService.getMovies().results.map { it.toThinMovie() }
                    .also {
                        movieMemorySource.cacheThinMovies(it)
                    }
        } catch (exception: Exception) {
            throw ServiceException(exception.message, exception)
        }
    }
}