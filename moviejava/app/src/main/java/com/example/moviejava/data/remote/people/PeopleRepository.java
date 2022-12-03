package com.example.moviejava.data.remote.people;


import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.Person;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PeopleRepository {
    private PeopleService rf;

    @Inject
    public PeopleRepository(Retrofit retrofit) {
        rf = retrofit.create(PeopleService.class);
    }

    public @NonNull Observable<Person> getPerson(int id) {
        return rf.getPerson(id).subscribeOn(Schedulers.io());
    }

    public @NonNull Observable<JsonObject> getMovieByPerson(int id) {
        return rf.getMovieByPerson(id).subscribeOn(Schedulers.io());

    }
}
