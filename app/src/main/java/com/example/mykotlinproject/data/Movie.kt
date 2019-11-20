package com.example.mykotlinproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class MovieList @JvmOverloads constructor(
    @Json(name = "page") val page: String,
    @Json(name = "results") val results:List<Movie>
)

@Entity(tableName = "movies")
data class Movie @JvmOverloads constructor(
    @Json(name = "id")
    @PrimaryKey @ColumnInfo(name = "movie_id")
    val mMovieID: String="0",
    @Json(name = "poster_path")
    @ColumnInfo(name = "image")
    val mImage: String="0",
    @Json(name = "title")
    @ColumnInfo(name = "title")
    val mTitle: String="0",
    @Json(name = "overview")
    @ColumnInfo(name = "synopsis")
    val mSynopsis: String="0",
    @Json(name = "vote_average")
    @ColumnInfo(name = "user_rating")
    val mUserRating: String="0",
    @Json(name = "release_date")
    @ColumnInfo(name = "release_date")
    val mReleaseDate: String="0"
) {
    //TODO: ABC

}