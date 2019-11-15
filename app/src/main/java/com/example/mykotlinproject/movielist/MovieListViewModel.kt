package com.example.mykotlinproject.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.data.source.MovieRepository

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _data: LiveData<List<Movie>> = TODO()
    val data = _data


}

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MovieListViewModel(repository) as T)
}