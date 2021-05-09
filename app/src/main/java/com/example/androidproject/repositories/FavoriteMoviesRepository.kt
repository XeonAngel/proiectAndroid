package com.example.androidproject.repositories

import androidx.annotation.WorkerThread
import com.example.androidproject.dao.FavoriteMovieDao
import com.example.androidproject.models.FavoriteMovie
import kotlinx.coroutines.flow.Flow

class FavoriteMoviesRepository(private val favoriteMovieDao: FavoriteMovieDao) {

    val allMoviesByName: Flow<List<FavoriteMovie>> = favoriteMovieDao.getMoviesByName()

    val allMoviesByScore: Flow<List<FavoriteMovie>> = favoriteMovieDao.getMoviesByScore()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getMovieByID(imbdId: String): FavoriteMovie? {
        return favoriteMovieDao.getMovieByID(imbdId)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favMovie: FavoriteMovie) {
        favoriteMovieDao.insert(favMovie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(favMovie: FavoriteMovie) {
        favoriteMovieDao.update(favMovie)
    }
}