package com.example.moviejava.data.remote.film;


import com.example.moviejava.data.remote.Constant;
import com.example.moviejava.model.casts.CastsFilm;
import com.example.moviejava.model.film.Film;
import com.example.moviejava.model.film.video.Video;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmService {
    @GET("movie/{id}?api_key=" + Constant.KEY + "&language=en-US")
    Observable<Film> getFilm(@Path("id") int id);

    @GET("movie/{id}/videos?api_key=" + Constant.KEY + "&language=en-US")
    Observable<Video> getVideo(@Path("id") int id);

    @GET("movie/{id}/casts?api_key=" + Constant.KEY + "&language=en-US")
    Observable<CastsFilm> getCastFilm(@Path("id") int id);

}