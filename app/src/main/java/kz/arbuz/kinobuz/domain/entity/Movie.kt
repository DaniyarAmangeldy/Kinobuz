package kz.arbuz.kinobuz.domain.entity

data class Movie(
    val id: String,
    val title: String,
    val image: String,
    val year: Int,
    val rating: Float
)