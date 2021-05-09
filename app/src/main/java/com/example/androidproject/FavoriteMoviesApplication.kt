package com.example.androidproject

import android.app.Application
import com.example.androidproject.repositories.FavoriteMoviesRepository
import com.example.androidproject.roomDatabase.FavoriteMovieRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FavoriteMoviesApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { FavoriteMovieRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { FavoriteMoviesRepository(database.favoriteMovieDao()) }
}