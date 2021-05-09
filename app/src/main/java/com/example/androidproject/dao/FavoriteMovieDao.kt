package com.example.androidproject.dao

import androidx.room.*
import com.example.androidproject.models.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movies ORDER BY movieTitle ASC")
    fun getMoviesByName(): Flow<List<FavoriteMovie>>

    @Query("SELECT * FROM favorite_movies ORDER BY movieScore DESC")
    fun getMoviesByScore(): Flow<List<FavoriteMovie>>

    @Query("SELECT * FROM favorite_movies WHERE movieIMBDId LIKE :imbdId ORDER BY movieTitle ASC LIMIT 1")
    suspend fun getMovieByID(imbdId: String): FavoriteMovie?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: FavoriteMovie)

    @Update()
    suspend fun update(movie: FavoriteMovie)

    @Query("DELETE FROM favorite_movies")
    suspend fun deleteAllMovies()
}