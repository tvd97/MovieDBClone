package com.example.moviejava.data.remote.film;


import com.example.moviejava.model.casts.CastsFilm;
import com.example.moviejava.model.film.Film;
import com.example.moviejava.model.film.video.Video;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FilmRepository {
    @Inject
    public FilmRepository(Retrofit retrofit) {
        this.rf = retrofit.create(FilmService.class);
    }

    private FilmService rf;

    public Observable<Film> getFilm(int id) {
        return rf.getFilm(id).subscribeOn(Schedulers.io());
    }
    public  Observable<Video> getVideo(int id)
    {
        return  rf.getVideo(id).subscribeOn(Schedulers.io());
    }
    public  Observable<CastsFilm> getCast(int id)
    {
        return  rf.getCastFilm(id).subscribeOn(Schedulers.io());
    }
//    fun getFilm(id: Int) = flow {
//        emit(
//            rf.getFilm(id = id)
//        )
//    }.flowOn(Dispatchers.IO)
//
//    fun getVideo(id: Int) = flow {
//        emit(
//            rf.getVideo(id = id)
//        )
//    }.flowOn(Dispatchers.IO)
//
//    fun getCastFilm(id: Int) = flow {
//        emit(rf.getCastFilm(id = id))
//    }.flowOn(Dispatchers.IO)
}