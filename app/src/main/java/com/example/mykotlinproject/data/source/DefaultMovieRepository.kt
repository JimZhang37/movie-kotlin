package com.example.mykotlinproject.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.data.Result
import com.example.mykotlinproject.data.source.remote.MoviesRemoteDataSource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

import com.example.mykotlinproject.data.Result.Success
import com.example.mykotlinproject.data.source.local.MoviesLocalDataSource

class DefaultMovieRepository(private val localDataSource: MoviesLocalDataSource) {


    suspend fun download() {


        val result = MoviesRemoteDataSource.download()
        if (result is Success) {
            result.data.forEach { movie ->
                localDataSource.saveMovie(movie)
            }
        }
    }

    fun observeMovies(): LiveData<List<Movie>> {
        return localDataSource.observerMovies()
    }
}