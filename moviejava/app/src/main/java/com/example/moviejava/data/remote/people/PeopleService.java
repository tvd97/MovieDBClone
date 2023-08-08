package com.example.moviejava.data.remote.people;


import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.Person;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeopleService {
    @GET("person/{person_id}")
    Observable<Person> getPerson(@Path("person_id") int id);

    @GET("discover/movie")
    Observable<JsonObject> getMovieByPerson(@Query("with_people") int id);
}