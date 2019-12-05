package com.example.mykotlinproject.data.source.local

import androidx.lifecycle.LiveData
import com.example.mykotlinproject.data.*

class MoviesLocalDataSource(
    private val moviesDao: MoviesDao,
    private val reviewsDao: ReviewsDao,
    private val trailersDao: TrailersDao
) {


    suspend fun saveMoviePopular(movie: Movie, moviePopular: MoviePopular) {
        moviesDao.insertMoviesPopularTransaction(movie,moviePopular)
    }

    suspend fun saveMovieTopRated(movie: Movie, movieTopRated: MovieTopRated) {
        moviesDao.insertMoviesTopRatedTransaction(movie,movieTopRated)
    }

    /**
     * It's time to deprecate this function
     */
    fun observeMovies(): LiveData<List<Movie>> {
        return moviesDao.observeMovies()
    }

    fun observeMoviesPopular(): LiveData<List<Movie>> {
        return moviesDao.observeMoviesPopular()
    }

    fun observeMoviesTopRated(): LiveData<List<Movie>> {
        return moviesDao.observeMoviesTopRated()
    }

    fun observeMovie(movieId: String): LiveData<Movie> {
        return moviesDao.observeMovieById(movieId)
    }

    suspend fun saveReview(review: ReviewDB) {
        reviewsDao.insertReview(review)
    }

    suspend fun saveTrailer(trailer: TrailerDB) {
        trailersDao.insertTrailer(trailer)
    }

    fun observeTrailersByMovieId(movieId: String): LiveData<List<TrailerDB>> {
        return trailersDao.observeTrailersByMovieId(movieId)
    }

    fun observeReviewsByMovieId(movieId: String): LiveData<List<ReviewDB>> {
        return reviewsDao.observeReviewsByMovieId(movieId)
    }
}