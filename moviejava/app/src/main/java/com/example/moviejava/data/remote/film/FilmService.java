package com.example.moviejava.data.remote.film;


import com.example.moviejava.data.remote.Constant;
import com.example.moviejava.model.casts.CastsFilm;
import com.example.moviejava.model.film.Film;
import com.example.moviejava.model.film.video.Video;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmService {
    @GET("movie/{id}")
    Observable<Film> getFilm(@Path("id") int id);

    @GET("movie/{id}/videos")
    Observable<Video> getVideo(@Path("id") int id);

    @GET("movie/{id}/casts")
    Observable<CastsFilm> getCastFilm(@Path("id") int id);

}