package com.example.androidproject.api

import com.example.androidproject.models.JsonResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("http://www.omdbapi.com/")
    fun getMovies(
        @Query("apikey") apikey: String,
        @Query("s") showName: String,
        @Query("page") pageNumber: String
    ): Call<JsonResult>

    companion object {
        private const val BASE_URL = "http://www.omdbapi.com/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}