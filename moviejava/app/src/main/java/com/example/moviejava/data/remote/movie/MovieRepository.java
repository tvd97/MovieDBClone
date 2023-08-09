package com.example.moviejava.data.remote.movie;


import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.up_coming.UpComing;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

@Singleton
public class MovieRepository {
    @Inject
    public MovieRepository(Retrofit retrofit) {
        this.service = retrofit.create(MovieService.class);
    }


    private MovieService service;

    public Observable<JsonObject> getTopRate(int page) {
        return service.getTopRate(page);
    }

    public Observable<UpComing> getUpcoming(int page) {
        return service.getUpcoming(page);
    }

    public Observable<JsonObject> getPopular(int page) {
        return service.getPopular(page);
    }

    public Observable<UpComing> getNowPlaying(int page) {
        return service.getNowPlaying(page);
    }

    public Observable<JsonObject> searchMovie(String query, int page) {
        return service.searchMovie(query, page);
    }
}