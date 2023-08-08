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
        this.rf = retrofit.create(MovieService.class);
    }


    private MovieService rf;

    public Observable<JsonObject> getTopRate(int page) {
        return rf.getTopRate(page);
    }

    public Observable<UpComing> getUpcoming(int page) {
        return rf.getUpcoming(page);
    }

    public Observable<JsonObject> getPopular(int page) {
        return rf.getPopular(page);
    }

    public Observable<UpComing> getNowPlaying(int page) {
        return rf.getNowPlaying(page);
    }

    public Observable<JsonObject> searchMovie(String query, int page) {
        return rf.searchMovie(query, page);
    }
}