package com.example.ubbassignment2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.ubbassignment2.model.ThinMovie
import com.example.ubbassignment2.repository.GenreRepository
import com.example.ubbassignment2.repository.MovieRepository
import com.example.ubbassignment2.service.ServiceException
import com.example.ubbassignment2.usecase.PerformGetMoviesUseCase
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val screenState = MutableLiveData<ScreenState>()
    val isLoading = screenState.map { it == ScreenState.Loading }
    val isNormal = screenState.map { it == ScreenState.Normal }
    val isError = screenState.map { it == ScreenState.Error }
    private val performGetMoviesUseCase: PerformGetMoviesUseCase = PerformGetMoviesUseCase(
        MovieRepository(),
        GenreRepository()
    )

    private val _movieList: MutableLiveData<List<ThinMovie>> = MutableLiveData()
    val movieList: LiveData<List<ThinMovie>> get() = _movieList

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            screenState.value = ScreenState.Loading
            try {

                _movieList.value = performGetMoviesUseCase()

                screenState.value = ScreenState.Normal
            } catch (exception: ServiceException) {
                screenState.value = ScreenState.Error
            }
        }
    }

    fun retry() {
        getMovies()
    }
}