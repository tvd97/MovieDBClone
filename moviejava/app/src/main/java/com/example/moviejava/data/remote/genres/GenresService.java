package com.example.moviejava.data.remote.genres;


import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.genres.FromJson;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenresService {
    @GET("genre/movie/list")
    Observable<FromJson> getListGenres();

    @GET("discover/movie")
    Observable<JsonObject> getListFilmByGenres(@Query("without_genres") int id, @Query("page") int page);
}