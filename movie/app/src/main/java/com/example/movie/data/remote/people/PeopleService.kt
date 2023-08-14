package com.example.movie.data.remote.people

import com.example.movie.data.remote.Constant
import com.example.movie.model.film.Film
import com.example.movie.model.JsonObject
import com.example.movie.model.Person
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {
    @GET("person/{person_id}")
    suspend fun getPerson(@Path("person_id") id: Int): Person

    @GET("discover/movie")
    suspend fun getMovieByPerson(@Query("with_people") id: Int): JsonObject
}