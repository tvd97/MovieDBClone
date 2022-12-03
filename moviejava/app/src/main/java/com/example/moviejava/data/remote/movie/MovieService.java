package com.example.moviejava.data.remote.movie;


import com.example.moviejava.data.remote.Constant;
import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.up_coming.UpComing;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/top_rated?api_key=" + Constant.KEY + "&language=en-US")
    Observable<JsonObject> getTopRate(@Query("page") int page);

    @GET("movie/upcoming?api_key=" + Constant.KEY + "&language=en-US")
    Observable<UpComing> getUpcoming(@Query("page") int page);

    @GET("movie/popular?api_key=" + Constant.KEY + "&language=en-US")
    Observable<JsonObject> getPopular(@Query("page") int page);

    @GET("movie/now_playing?api_key=" + Constant.KEY + "&language=en-US")
    Observable<UpComing> getNowPlaying(@Query("page") int page);

    @GET("search/movie?api_key=" + Constant.KEY + "&language=en-US")
    Observable<JsonObject> searchMovie(@Query("query") String query, @Query("page") int page);


}