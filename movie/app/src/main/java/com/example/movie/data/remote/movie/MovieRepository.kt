package com.example.movie.data.remote.movie

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject

class MovieRepository @Inject constructor(retrofit: Retrofit) {
    private val service = retrofit.create(MovieService::class.java)
    fun getUpcoming(page: Int) = flow {
        emit(
            service.getUpcoming(page = page)
        )
    }.flowOn(Dispatchers.IO)

    fun getTopRate(page: Int) = flow {
        emit(
            service.getTopRate(page = page)
        )
    }.flowOn(Dispatchers.IO)

    fun getNowPlaying(page: Int) = flow {
        emit(service.getNowPlaying(page = page))
    }.flowOn(Dispatchers.IO)

    fun getPopular(page: Int) = flow {
        emit(service.getPopular(page = page))
    }.flowOn(Dispatchers.IO)

    fun searchMovie(query: String, page: Int) = flow {
        emit(service.searchMovie(query = query, page = page))
    }.flowOn(Dispatchers.IO)
}