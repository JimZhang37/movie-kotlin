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

package com.example.mykotlinproject

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
//import com.example.android.architecture.blueprints.todoapp.data.source.DefaultTasksRepository
//import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
//import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
//import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
//import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
//import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource
import com.example.mykotlinproject.data.source.DefaultMovieRepository
import com.example.mykotlinproject.data.source.local.MovieDatabase
import com.example.mykotlinproject.data.source.local.MoviesLocalDataSource
import com.example.mykotlinproject.data.source.remote.MoviesRemoteDataSource
import kotlinx.coroutines.runBlocking

/**
 * A Service Locator for the [TasksRepository]. This is the prod version, with a
 * the "real" [TasksRemoteDataSource].
 */
object ServiceLocator {

    private val lock = Any()
    private var database: MovieDatabase? = null
    @Volatile
    var moviesRepository: DefaultMovieRepository? = null
        @VisibleForTesting set


    fun provideMoviesRepository(context: Context): DefaultMovieRepository {
        synchronized(this) {
            return moviesRepository ?: createMoviesRepository(context)
        }
    }

    private fun createMoviesRepository(context: Context): DefaultMovieRepository {
        val newRepo = DefaultMovieRepository( createTaskLocalDataSource(context))
        moviesRepository = newRepo
        return newRepo
    }

    private fun createTaskLocalDataSource(context: Context): MoviesLocalDataSource {
        val database = database ?: createDataBase(context)
        return MoviesLocalDataSource(database.movieDao(),database.reviewDao(),database.trailerDao())
    }

    private fun createDataBase(context: Context): MovieDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java, "Movies.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
//            runBlocking {
//                MoviesRemoteDataSource.deleteAllTasks()
//            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            moviesRepository = null
        }
    }
}
