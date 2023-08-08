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
    private PeopleService rf;

    @Inject
    public PeopleRepository(Retrofit retrofit) {
        rf = retrofit.create(PeopleService.class);
    }

    public @NonNull Observable<Person> getPerson(int id) {
        return rf.getPerson(id);
    }

    public @NonNull Observable<JsonObject> getMovieByPerson(int id) {
        return rf.getMovieByPerson(id);

    }
}
