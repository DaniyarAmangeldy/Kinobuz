package kz.arbuz.kinobuz.domain.usecase

import kz.arbuz.kinobuz.data.api.ImdbService
import kz.arbuz.kinobuz.domain.entity.Movie

class GetTop250MoviesUseCase(
    private val service: ImdbService
) {

    suspend fun invoke(): Result<List<Movie>> = try {
        Result.Success(service.getMovies().items.map { it.mapToDomain() })
    } catch (e: Throwable) {
        Result.Error(e.localizedMessage ?: e.message ?: e.toString())
    }


    sealed class Result<out T> {
        class Success<out T>(val data: T?) : Result<T>()
        class Error(val message: String) : Result<Nothing>()
    }
}