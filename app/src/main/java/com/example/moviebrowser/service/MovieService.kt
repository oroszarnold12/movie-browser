package com.example.ubbassignment2.service

import com.example.ubbassignment2.dto.MovieDto
import com.example.ubbassignment2.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/top_rated")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = "5d4041c745287fa25caf851fc8086e87"
    ): MovieListDto

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = "5d4041c745287fa25caf851fc8086e87"
    ): MovieDto
}