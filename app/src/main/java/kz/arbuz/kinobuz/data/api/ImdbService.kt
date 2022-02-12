package kz.arbuz.kinobuz.data.api

import kz.arbuz.kinobuz.BuildConfig
import kz.arbuz.kinobuz.data.entity.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * --> API URL <--
 * https://imdb-api.com/api
 *
 * API KEY IN BUILD CONFIG
 */
interface ImdbService {

    @GET("ru/API/Top250Movies/{apiKey}")
    suspend fun getMovies(
        @Path("apiKey") apiKey: String = BuildConfig.API_KEY
    ): MovieListResponse
}