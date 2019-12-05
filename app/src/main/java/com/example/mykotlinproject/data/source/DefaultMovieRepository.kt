package com.example.mykotlinproject.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mykotlinproject.data.*
import com.example.mykotlinproject.data.source.remote.MoviesRemoteDataSource

import com.example.mykotlinproject.data.Result.Success
import com.example.mykotlinproject.data.source.local.MoviesLocalDataSource

class DefaultMovieRepository(private val localDataSource: MoviesLocalDataSource) {

    suspend fun download() {
        downloadMoviePopular()
        downloadMovieTopRated()
    }

    private suspend fun downloadMoviePopular() {
        val result = MoviesRemoteDataSource.downloadPopularMovies()
        if (result is Success) {
            result.data.forEach { movie ->
                val moviePopular = MoviePopular(movie.mMovieID)
                localDataSource.saveMoviePopular(movie, moviePopular)
            }
        }
    }

    private suspend fun downloadMovieTopRated() {
        val result = MoviesRemoteDataSource.downloadTopRated()
        if (result is Success) {
            result.data.forEach { movie ->
                val movieTopRated = MovieTopRated(movie.mMovieID)
                localDataSource.saveMovieTopRated(movie, movieTopRated)
            }
        }
    }

    /**
     * It's time to deprecate this function
     */
    fun observeMovies(): LiveData<List<Movie>> {
        return localDataSource.observeMovies()
    }

    fun observeMoviesTopRated(): LiveData<List<Movie>> {
        return localDataSource.observeMoviesTopRated()
    }

    fun observeMoviesPopular(): LiveData<List<Movie>> {
        return localDataSource.observeMoviesPopular()
    }

    fun observeMovie(movieId: String): LiveData<Movie> {
        return localDataSource.observeMovie(movieId)
    }

    fun observeTrailers(movieId: String): LiveData<List<TrailerDB>> {
        return localDataSource.observeTrailersByMovieId(movieId)
    }

    fun observeReviews(movieId: String): LiveData<List<ReviewDB>> {
        return localDataSource.observeReviewsByMovieId(movieId)
    }

    suspend fun downloadMovieDetails(movieId: String) {
        val resultReviews = MoviesRemoteDataSource.downloadMovieReviews(movieId)
        if (resultReviews is Success) {
            resultReviews.data.forEach { review ->
                val reviewdb = ReviewDB(review, movieId)
                Log.d("downloadMovieDetails: reviews", reviewdb.toString())

                localDataSource.saveReview(reviewdb)
            }
        }

        val resultTrailers = MoviesRemoteDataSource.downloadMovieTrailers(movieId)
        if (resultTrailers is Success) {
            resultTrailers.data.forEach { trailer ->
                val trailerdb = TrailerDB(trailer, movieId)
                Log.d("downloadMovieDetails: trailers", trailerdb.toString())
                localDataSource.saveTrailer(trailerdb)
            }
        }
    }


}