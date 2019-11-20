package com.example.mykotlinproject.data.source.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mykotlinproject.data.Movie

//import com.example.android.architecture.blueprints.todoapp.data.Task

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesDao


}

object ServiceLocator {

    private val LOG_TAG = MovieDatabase::class.java!!.getSimpleName()

    private val DATABASE_NAME = "movies_db"
    private var sInstance: MovieDatabase? = null

    fun getInstance(context: Context): MovieDatabase? {
        if (sInstance == null) {

            Log.d(LOG_TAG, "Creating new database instance")
            sInstance = Room.databaseBuilder<MovieDatabase>(
                context.applicationContext,
                MovieDatabase::class.java!!, DATABASE_NAME
            )
                .build()
        }

        Log.d(LOG_TAG, "Getting the database instance")
        return sInstance
    }
}