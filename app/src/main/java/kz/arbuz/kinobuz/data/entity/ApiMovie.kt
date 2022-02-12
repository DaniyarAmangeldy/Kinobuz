package kz.arbuz.kinobuz.data.entity

import kz.arbuz.kinobuz.domain.entity.Movie

data class ApiMovie(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val rating: Float
) {
    fun mapToDomain(): Movie = Movie(id, title, image, year, rating)
}