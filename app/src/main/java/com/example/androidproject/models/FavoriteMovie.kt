package com.example.androidproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @ColumnInfo(name = "movieTitle") override var title: String,
    @ColumnInfo(name = "movieYear") override var year: String,
    @PrimaryKey @ColumnInfo(name = "movieIMBDId") override var imdbID: String,
    @ColumnInfo(name = "movieScore") var score: Int
) : DbObject