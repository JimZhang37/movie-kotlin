package com.example.mykotlinproject.data.source.local

/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mykotlinproject.data.*


@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun observeMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun observeMovieById(movieId: String): LiveData<Movie>

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    suspend fun getMovieById(movieId: String): Movie?

    @Query("DELETE FROM movies")
    suspend fun deleteMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePopular(movie: MoviePopular)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieToprated(movie: MovieTopRated)

    /**
     * intert movies into both Movie table and MoviePopular table
     */
    @Transaction
    suspend fun insertMoviesPopularTransaction(movie: Movie,moviePopular: MoviePopular) {
        insertMoviePopular(moviePopular)
        insertMovie(movie)
    }

    /**
     * intert movies into both Movie table and MovieTopRated table
     */
    @Transaction
    suspend fun insertMoviesTopRatedTransaction(movie: Movie,movieTopRated: MovieTopRated) {
        insertMovieToprated(movieTopRated)
        insertMovie(movie)
    }


    @Query("SELECT * FROM movies INNER JOIN movies_popular ON movies.movie_id = movies_popular.mMovieID")
    fun observeMoviesPopular(): LiveData<List<Movie>>


    @Query("SELECT * FROM movies INNER JOIN movies_top_rated ON movies.movie_id = movies_top_rated.mMovieID")
    fun observeMoviesTopRated(): LiveData<List<Movie>>
}
