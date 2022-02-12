package kz.arbuz.kinobuz.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kz.arbuz.kinobuz.domain.entity.Movie
import kz.arbuz.kinobuz.domain.usecase.GetTop250MoviesUseCase

class MovieViewModel(
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase
) : ViewModel() {

    private val _top250Movies = MutableLiveData<List<Movie>>(emptyList())
    val top250Movies: LiveData<List<Movie>> = _top250Movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        when (val result = getTop250MoviesUseCase.invoke()) {
            is GetTop250MoviesUseCase.Result.Success -> _top250Movies.value = result.data
            is GetTop250MoviesUseCase.Result.Error -> _error.value = result.message
        }
    }
}