package com.example.moviejava.data.remote.people;


import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.Person;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

@Singleton
public class PeopleRepository {
    private PeopleService service;

    @Inject
    public PeopleRepository(Retrofit retrofit) {
        service = retrofit.create(PeopleService.class);
    }

    public @NonNull Observable<Person> getPerson(int id) {
        return service.getPerson(id);
    }

    public @NonNull Observable<JsonObject> getMovieByPerson(int id) {
        return service.getMovieByPerson(id);

    }
}
