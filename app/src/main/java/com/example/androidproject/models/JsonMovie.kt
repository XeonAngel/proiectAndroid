package com.example.androidproject.models

import com.google.gson.annotations.SerializedName

data class JsonMovie(
    @SerializedName("Title")
    override var title: String,
    @SerializedName("Year")
    override var year: String,
    override var imdbID: String,
    var Type: String,
    var Poster: String
): DbObject