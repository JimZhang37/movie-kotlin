package com.example.mykotlinproject.data

import androidx.room.*
import com.squareup.moshi.Json


data class MovieList constructor(
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

/**
 * The popular movies'id will be stored here, while movie information is stored in movie table
 */
@Entity(tableName = "movies_popular")
data class MoviePopular(
    @PrimaryKey
    val mMovieID: String
)

@Entity(tableName = "movies_top_rated")
data class MovieTopRated(
    @PrimaryKey
    val mMovieID: String
)

//
//
//@DatabaseView("SELECT movies.movie_id, movies.image, movies.title, movies.synopsis, movies.user_rating, movies.release_date " +
//        "FROM movies " +
//        "INNER JOIN movies_popular ON movies.movie_id = movies_popular.mMovieID")
//data class MoviePopularView(
//    val movie_id: String,
//    val image: String,
//    val title: String,
//    val synopsis: String,
//    val user_rating: String,
//    val release_date: String
//)
//
//@DatabaseView("SELECT movies.movie_id, movies.image, movies.title, movies.synopsis, movies.user_rating, movies.release_date " +
//        "FROM movies " +
//        "INNER JOIN movies_top_rated ON movies.movie_id = movies_top_rated.mMovieID")
//data class MovieTopRatedView(
//    val movie_id: String,
//    val image: String,
//    val title: String,
//    val synopsis: String,
//    val user_rating: String,
//    val release_date: String
//)