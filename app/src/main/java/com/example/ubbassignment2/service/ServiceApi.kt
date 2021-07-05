package com.example.ubbassignment2.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceApi {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val genreService: GenreService by lazy {
        retrofit.create(GenreService::class.java)
    }

    val movieService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

}