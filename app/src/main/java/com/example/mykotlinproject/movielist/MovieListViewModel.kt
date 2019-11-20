package com.example.mykotlinproject.movielist

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.mykotlinproject.Event
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.data.source.local.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.mykotlinproject.data.source.remote.MovieApi

class MovieListViewModel(application: Application

) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext
    private val db = ServiceLocator.getInstance(context)

    private var _data = db?.movieDao()?.observeMovies()
    val data
        get() = _data

    fun download() {
        val list = MovieApi.retrofitService.getProperties()
        viewModelScope.launch(Dispatchers.IO) {
            println("before internet connection")
            val list = MovieApi.retrofitService.getProperties().await()

            list.results.forEach { movie -> db?.movieDao()?.insertMovie(movie) }
            println("after internet connection")
           // println(list.results.toString())
        }
        //_data.value = list.results
    }

    fun save(movie: Movie) {

    }
//    init {

//    }
}
//
//@Suppress("UNCHECKED_CAST")
//class MovieListViewModelFactory(
//
//) : ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel> create(modelClass: Class<T>) =
//        (MovieListViewModel(application = Application()) as T)
//}