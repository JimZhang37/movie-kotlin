package com.example.mykotlinproject.data.source.remote

import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.data.Result
import com.example.mykotlinproject.data.Result.Success
import com.example.mykotlinproject.data.Result.Error
import com.example.mykotlinproject.data.Review
import com.example.mykotlinproject.data.Trailer
import com.squareup.moshi.JsonDataException


object MoviesRemoteDataSource {


    suspend fun downloadPopularMovies(): Result<List<Movie>> {

        val list = MovieApi.retrofitService.getPopularMoviesAsync().await()
        return Success(list.results)

    }

    suspend fun downloadTopRated(): Result<List<Movie>> {

        val list = MovieApi.retrofitService.getTopRatedMoviesAsync().await()
        return Success(list.results)

    }

    suspend fun downloadMovieReviews(movieId: String): Result<List<Review>> {

        return try {
            val list = MovieApi.retrofitService.getMovieReviewsAsync(movieId).await()
            Success(list.results)
        } catch (e: JsonDataException) {
            Error(e)
        }

    }

    suspend fun downloadMovieTrailers(movieId: String): Result<List<Trailer>> {
        return try {
            val list = MovieApi.retrofitService.geMovieTrailersAsync(movieId).await()
            return Success(list.results)
        } catch (e: JsonDataException) {
            Error(e)
        }
    }
}