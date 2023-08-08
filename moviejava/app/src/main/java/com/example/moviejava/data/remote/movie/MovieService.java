package com.example.moviejava.data.remote.movie;


import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.up_coming.UpComing;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/top_rated")
    Observable<JsonObject> getTopRate(@Query("page") int page);

    @GET("movie/upcoming")
    Observable<UpComing> getUpcoming(@Query("page") int page);

    @GET("movie/popular")
    Observable<JsonObject> getPopular(@Query("page") int page);

    @GET("movie/now_playing")
    Observable<UpComing> getNowPlaying(@Query("page") int page);

    @GET("search/movie")
    Observable<JsonObject> searchMovie(@Query("query") String query, @Query("page") int page);


}