package com.example.movie.data.remote.film


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject

class FilmRepository @Inject constructor(val retrofit: Retrofit) {
    private val service = retrofit.create(FilmService::class.java)
    fun getFilm(id: Int) = flow {
        emit(
            service.getFilm(id = id)
        )
    }.flowOn(Dispatchers.IO)

    fun getVideo(id: Int) = flow {
        emit(
            service.getVideo(id = id)
        )
    }.flowOn(Dispatchers.IO)

    fun getCastFilm(id: Int) = flow {
        emit(service.getCastFilm(id = id))
    }.flowOn(Dispatchers.IO)
}