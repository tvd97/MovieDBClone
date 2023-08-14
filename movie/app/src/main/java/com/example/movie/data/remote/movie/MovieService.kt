package com.example.movie.data.remote.movie

import com.example.movie.data.remote.Constant
import com.example.movie.model.JsonObject
import com.example.movie.model.up_coming.UpComing
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/top_rated")
    suspend fun getTopRate(@Query("page") page: Int): JsonObject

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("page") page: Int): UpComing

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): JsonObject

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): UpComing

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String, @Query("page") page: Int): JsonObject


}