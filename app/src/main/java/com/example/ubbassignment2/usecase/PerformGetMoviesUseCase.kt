package com.example.ubbassignment2.usecase

import com.example.ubbassignment2.model.Genre
import com.example.ubbassignment2.model.ThinMovie
import com.example.ubbassignment2.repository.GenreRepository
import com.example.ubbassignment2.repository.MovieRepository

class PerformGetMoviesUseCase internal constructor(
    private val movieRepository: MovieRepository,
    private val genreRepository: GenreRepository
) {
    suspend operator fun invoke(): List<ThinMovie> {
        val movies: List<ThinMovie> = movieRepository.getMovies()

        val genres: List<Genre> = genreRepository.getGenres()

        movies.forEach { movie ->
            val movieGenres: List<String> = genres.filter { genre ->
                movie.genreIds.contains(genre.id)
            }.map { genreDto -> genreDto.name }

            movie.chips.addAll(movieGenres)
        }

        return movies
    }
}