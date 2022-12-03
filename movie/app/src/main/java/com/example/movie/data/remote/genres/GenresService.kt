package com.example.movie.data.remote.genres

import com.example.movie.data.remote.Constant
import com.example.movie.model.genres.FromJson
import com.example.movie.model.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresService {
    @GET("genre/movie/list?api_key=${Constant.KEY}&language=en-US")
    suspend fun getListGenres(): FromJson

    @GET("discover/movie?api_key=${Constant.KEY}&language=en-US")
    suspend fun getListFilmByGenres(@Query("without_genres") id: Int,@Query("page") page:Int): JsonObject
}