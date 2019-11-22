package com.example.mykotlinproject.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.data.Result
import com.example.mykotlinproject.data.Result.Success



object MoviesRemoteDataSource{


    suspend fun download(): Result<List<Movie>>{

            val list = MovieApi.retrofitService.getProperties().await()
            return Success(list.results)

    }
}