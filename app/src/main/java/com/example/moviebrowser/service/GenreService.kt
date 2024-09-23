package com.example.ubbassignment2.service

import com.example.ubbassignment2.dto.GenreListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = "5d4041c745287fa25caf851fc8086e87"
    ): GenreListDto
}