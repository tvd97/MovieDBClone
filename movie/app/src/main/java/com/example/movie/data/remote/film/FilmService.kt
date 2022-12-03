package com.example.movie.data.remote.film


import com.example.movie.data.remote.Constant
import com.example.movie.model.casts.CastsFilm
import com.example.movie.model.film.Film
import com.example.movie.model.film.video.Video
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmService {
    @GET("movie/{id}?api_key=${Constant.KEY}&language=en-US")
    suspend fun getFilm(@Path("id") id: Int): Film

    @GET("movie/{id}/videos?api_key=${Constant.KEY}&language=en-US")
    suspend fun getVideo(@Path("id") id: Int): Video

    @GET("movie/{id}/casts?api_key=${Constant.KEY}&language=en-US")
    suspend fun getCastFilm(@Path("id") id: Int): CastsFilm

}