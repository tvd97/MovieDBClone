package com.example.moviejava.data.remote.genres;


import androidx.annotation.NonNull;

import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.genres.FromJson;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

@Singleton
public class GenresRepository {
    @NonNull
    private final GenresService service;

    @Inject
    public GenresRepository(@NonNull Retrofit retrofit) {
       this.service = retrofit.create(GenresService.class);
    }


    public Observable<FromJson> getGenres() {

        return service.getListGenres();
    }

    public Observable<JsonObject> getListGenresByFilm(int id, int page) {

        return service.getListFilmByGenres(id, page);
    }

}