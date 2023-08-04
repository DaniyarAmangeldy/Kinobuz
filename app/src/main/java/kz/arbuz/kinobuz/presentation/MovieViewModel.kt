package kz.arbuz.kinobuz.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kz.arbuz.kinobuz.data.entity.ApiMovie
import kz.arbuz.kinobuz.domain.usecase.GetTop250MoviesUseCase

class MovieViewModel(
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase
): ViewModel() {

    private val _top250Movies = MutableLiveData<List<ApiMovie>>(emptyList())
    val top250Movies: LiveData<List<ApiMovie>> = _top250Movies

    fun dispatch(action: Action) {
        when (action) {
            Action.onCreated -> {
                GlobalScope.launch {
                    _top250Movies.value = getTop250MoviesUseCase.invoke()
                }
            }
        }
    }

    sealed class Action {
        object onCreated: Action()
    }
}