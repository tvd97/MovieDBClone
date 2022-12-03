package com.example.movie.data.remote.genres

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenresRepository @Inject constructor(val retrofit: Retrofit) {
    private val rf = retrofit.create(GenresService::class.java)
    fun getGenres() = flow {
        emit(
            rf.getListGenres()
        )
    }.flowOn(Dispatchers.IO)

    fun getListFilmByGenres(id: Int, page: Int) = flow {
        emit(rf.getListFilmByGenres(id = id, page = page))
    }.flowOn(Dispatchers.IO)
}