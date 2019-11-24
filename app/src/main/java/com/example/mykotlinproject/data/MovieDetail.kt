package com.example.mykotlinproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class Review(
    @Json(name = "author")
    val author: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val url: String
)

data class ReviewList(
    @Json(name = "id") val id: String,
    @Json(name = "page") val page: String,
    @Json(name = "results") val results: List<Review>,
    @Json(name = "total_pages") val total_pages: String,
    @Json(name = "total_results") val total_results: String
)

@Entity(tableName = "review")
data class ReviewDB(
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "content")
    val content: String,
    @PrimaryKey @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "movie_id")
    val movieId: String
) {
    constructor(review: Review, movieId: String) : this(
        author = review.author,
        content = review.content,
        id = review.id,
        url = review.url,
        movieId = movieId
    )
}

data class Trailer(
    @Json(name = "id")
    val id: String,
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "site")
    val site: String,
    @Json(name = "type")
    val type: String
)

data class TrailerList(
    @Json(name = "id")
    val id: String,
    @Json(name = "results")
    val results: List<Trailer>
)

@Entity(tableName = "trailer")
data class TrailerDB(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "key")
    val key: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "site")
    val site: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "movie_id")
    val movieId: String
) {
    constructor(trailer: Trailer, movieId: String) : this(
        id = trailer.id,
        key = trailer.key,
        name = trailer.name,
        site = trailer.site,
        type = trailer.type,
        movieId = movieId
    )
}