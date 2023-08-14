package com.example.movie.data.remote.people

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import javax.inject.Inject

class PeopleRepository @Inject constructor(val retrofit: Retrofit) {
    private val service = retrofit.create(PeopleService::class.java)
    fun getPerson(id: Int) = flow {
        emit(service.getPerson(id))
    }.flowOn(Dispatchers.IO)

    fun getMovieByPerson(id: Int) = flow {
        emit(service.getMovieByPerson(id))
    }.flowOn(Dispatchers.IO)
}
