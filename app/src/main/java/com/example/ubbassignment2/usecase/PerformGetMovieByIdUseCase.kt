package com.example.ubbassignment2.usecase

import com.example.ubbassignment2.model.Movie
import com.example.ubbassignment2.repository.MovieRepository

class PerformGetMovieByIdUseCase internal constructor(
    private val movieRepository: MovieRepository
    ) {
    suspend operator fun invoke(id: Int): Movie {
        return movieRepository.getMovie(id)
    }
}