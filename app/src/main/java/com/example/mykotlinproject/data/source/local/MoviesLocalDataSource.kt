package com.example.mykotlinproject.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.mykotlinproject.data.Movie

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesLocalDataSource(private val moviesDao: MoviesDao){



    suspend fun saveMovie(movie: Movie){
        moviesDao.insertMovie(movie)
    }

    fun observerMovies():LiveData<List<Movie>>{
        return moviesDao.observeMovies()
    }
}