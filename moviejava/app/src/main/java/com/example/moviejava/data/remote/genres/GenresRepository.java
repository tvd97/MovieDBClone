package com.example.moviejava.data.remote.genres;


import androidx.annotation.NonNull;

import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.genres.FromJson;

import javax.inject.Inject;
import javax.inject.Singleton;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

@Singleton
public class GenresRepository {
    @NonNull
    private final GenresService rf;

    @Inject
    public GenresRepository(@NonNull Retrofit retrofit) {
       this.rf = retrofit.create(GenresService.class);
    }


    public Observable<FromJson> getGenres() {

        return rf.getListGenres().subscribeOn(Schedulers.io());
    }

    public Observable<JsonObject> getListGenresByFilm(int id, int page) {

        return rf.getListFilmByGenres(id, page).subscribeOn(Schedulers.io());
    }

}