package com.example.moviejava.data.remote.film;


import com.example.moviejava.model.casts.CastsFilm;
import com.example.moviejava.model.film.Film;
import com.example.moviejava.model.film.video.Video;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

@Singleton
public class FilmRepository {
    @Inject
    public FilmRepository(Retrofit retrofit) {
        this.service = retrofit.create(FilmService.class);
    }

    private FilmService service;

    public Observable<Film> getFilm(int id) {
        return service.getFilm(id);
    }

    public Observable<Video> getVideo(int id) {
        return service.getVideo(id);
    }

    public Observable<CastsFilm>getCast(int id) {
        return service.getCastFilm(id);
    }
}