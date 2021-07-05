package com.example.ubbassignment2.memory

import com.example.ubbassignment2.model.Movie
import com.example.ubbassignment2.model.ThinMovie

class MovieMemorySource {
    private var thinMovies: List<ThinMovie>? = null
    private var movies: MutableMap<Int, Movie> = HashMap()

    fun cacheThinMovies(thinMovies: List<ThinMovie>) {
        this.thinMovies = thinMovies
    }

    fun cacheMovie(movie: Movie) {
        this.movies[movie.id] = movie
    }

    fun getMovie(id: Int): Movie? {
        return movies[id]
    }

    fun getMovies(): List<ThinMovie>? {
        return thinMovies
    }
}