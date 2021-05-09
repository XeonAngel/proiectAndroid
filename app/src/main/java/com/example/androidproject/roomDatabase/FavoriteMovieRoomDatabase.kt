package com.example.androidproject.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.androidproject.dao.FavoriteMovieDao
import com.example.androidproject.models.FavoriteMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class FavoriteMovieRoomDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoriteMovieDao

    private class FavoriteMovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Instance?.let { database ->
                scope.launch {
                    populateDatabase(database.favoriteMovieDao())
                }
            }
        }
        suspend fun populateDatabase(movieDao: FavoriteMovieDao){
            movieDao.deleteAllMovies()
//            var movie = FavoriteMovie(1, "Test", "2021", "tt9999", 99)
//            movieDao.insert(movie)
//            movie = FavoriteMovie(2, "Test2", "2022", "tt99999", 98)
//            movieDao.insert(movie)
        }
    }

    companion object {
        @Volatile
        private var Instance: FavoriteMovieRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FavoriteMovieRoomDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteMovieRoomDatabase::class.java,
                    "favorite_movie_database"
                )
                    .addCallback(FavoriteMovieDatabaseCallback(scope))
                    .build()
                Instance = instance
                instance
            }
        }
    }
}