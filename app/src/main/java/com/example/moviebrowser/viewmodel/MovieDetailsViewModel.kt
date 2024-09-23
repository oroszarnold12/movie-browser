package com.example.ubbassignment2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.ubbassignment2.model.Movie
import com.example.ubbassignment2.repository.MovieRepository
import com.example.ubbassignment2.service.ServiceException
import com.example.ubbassignment2.usecase.PerformGetMovieByIdUseCase
import kotlinx.coroutines.launch


class MovieDetailsViewModel : ViewModel() {
    private val screenState = MutableLiveData<ScreenState>()
    val isLoading = screenState.map { it == ScreenState.Loading }
    val isNormal = screenState.map { it == ScreenState.Normal }
    val isError = screenState.map { it == ScreenState.Error }
    private val performGetMovieByIdUseCase: PerformGetMovieByIdUseCase =
        PerformGetMovieByIdUseCase(MovieRepository())

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> get() = _movie

    fun getMovie(id: Int) {
        viewModelScope.launch {
            try {
                screenState.value = ScreenState.Loading

                _movie.value = performGetMovieByIdUseCase(id)

                screenState.value = ScreenState.Normal
            } catch (exception: ServiceException) {
                screenState.value = ScreenState.Error
            }
        }
    }

    fun retry(id: Int) {
        getMovie(id)
    }
}