package com.example.mykotlinproject.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mykotlinproject.data.*
import com.example.mykotlinproject.data.source.DefaultMovieRepository
import com.example.mykotlinproject.movielist.MovieListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(val repository: DefaultMovieRepository, val movieId: String) :
    ViewModel() {

    private val _movie: LiveData<Movie> = repository.observeMovie(movieId)
    val movie: LiveData<Movie>
        get() = _movie

    private val _reviews: LiveData<List<ReviewDB>> = repository.observeReviews(movieId)
    val reviews: LiveData<List<ReviewDB>>
        get() = _reviews

    private val _trailers: LiveData<List<TrailerDB>> = repository.observeTrailers(movieId)
    val trailers: LiveData<List<TrailerDB>>
        get() = _trailers



    fun getMovieDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.downloadMovieDetails(movieId)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MovieDetailViewModelFactory(
    private val repository: DefaultMovieRepository,
    private val movieId: String

) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MovieDetailViewModel(repository, movieId) as T)
}