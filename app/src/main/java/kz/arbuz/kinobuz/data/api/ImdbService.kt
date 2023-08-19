package kz.arbuz.kinobuz.data.api

import kz.arbuz.kinobuz.data.entity.ApiMovie
import retrofit2.http.GET

// https://imdb-api.com/api
interface ImdbService {

    @GET("ru/API/Top250Movies/")
    suspend fun getMovies(): List<ApiMovie>
}