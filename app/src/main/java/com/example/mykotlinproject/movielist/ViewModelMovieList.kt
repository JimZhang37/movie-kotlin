package com.example.mykotlinproject.movielist

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.mykotlinproject.Event
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.data.source.DefaultMovieRepository
//import com.example.mykotlinproject.data.source.local.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.mykotlinproject.data.source.remote.MovieApi

class MovieListViewModel(

    private val repository: DefaultMovieRepository
) : ViewModel() {
    //    private val context = getApplication<Application>().applicationContext
//    private val db = ServiceLocator.getInstance(context)


    val dataTopRated
        get() = _dataTopRated

    private var _dataTopRated = repository.observeMoviesTopRated()

    val dataPopular
        get() = _dataPopular

    private var _dataPopular = repository.observeMoviesPopular()



    fun download() {

        viewModelScope.launch(Dispatchers.IO) {
            repository.download()
//            println("before internet connection")
//            val list = MovieApi.retrofitService.getProperties().await()
//
//            list.results.forEach { movie -> db?.movieDao()?.insertMovie(movie) }
//            println("after internet connection")
//           // println(list.results.toString())
        }
        //_data.value = list.results
    }


//    init {

//    }
}

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(
    private val repository: DefaultMovieRepository

) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MovieListViewModel(repository) as T)
}