package com.example.mykotlinproject.data.source.remote

import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.data.MovieList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred

//import r

private const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/"

private const val PARAM_APIKEY = "api_key"

private const val PATH_POPULAR = "popular"

private const val PATH_TOP_RATED = "top_rated"

private const val PATH_VIDEOS = "videos"

private const val PATH_REVIEWS = "reviews"

private const val API_KEY = "758f975f610e3d276c8f2364e5052672"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(MOVIE_BASE_URL)
    .build()

interface MovieApiService {

    @GET(PATH_POPULAR)
    fun getProperties(@Query(PARAM_APIKEY) key: String = API_KEY):
            Deferred<MovieList>
}

object MovieApi {
    val retrofitService : MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}