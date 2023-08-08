package com.example.moviejava.ui.film;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviejava.data.local.dao.AppDao;
import com.example.moviejava.data.local.entities.MovieEntity;
import com.example.moviejava.data.remote.film.FilmRepository;
import com.example.moviejava.model.State;
import com.example.moviejava.model.casts.CastsFilm;
import com.example.moviejava.model.film.Film;
import com.example.moviejava.model.film.video.Video;
import com.example.moviejava.ui.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class FilmViewModel extends BaseViewModel {
    private FilmRepository repository;
    private AppDao dao;

    @Inject
    public FilmViewModel(FilmRepository repository, AppDao dao) {
        this.repository = repository;
        this.dao = dao;

    }

    private final MutableLiveData<State<Film>> _film = new MutableLiveData<>();
    public LiveData<State<Film>> film = _film;

    private final MutableLiveData<State<Video>> _video = new MutableLiveData<>();
    public LiveData<State<Video>> video = _video;

    private final MutableLiveData<State<CastsFilm>> _cast = new MutableLiveData<>();
    public LiveData<State<CastsFilm>> cast = _cast;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loadData(int id) {
        launchRxSingle(repository.getFilm(id),
                result -> _film.postValue(State.success(result)),
                null);
        launchRxSingle(
             repository.getVideo(id),
                result -> _video.postValue(State.success(result)),
                null);
        launchRxSingle(
               repository.getCast(id),
                result -> _cast.postValue(State.success(result)),
                null);
//        repository.getFilm(id).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Film>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Film film) {
//                        _film.setValue(film);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("complete", "not error");
//                    }
//                });
//        repository.getVideo(id).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Video>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Video video) {
//                        _video.setValue(video);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("complete", "not error");
//                    }
//                });
//        repository.getCast(id).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<CastsFilm>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull CastsFilm castsFilm) {
//                        _cast.setValue(castsFilm);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("complete", "not error");
//                    }
//                });
    }

    public void addToDb(Film f) {
        MovieEntity m = new MovieEntity();
        m.id = f.id;
        m.adult = f.adult;
        m.backdropPath = f.backdropPath;
        m.posterPath = f.posterPath;
        m.releaseDate = f.releaseDate;
        m.title = f.title;
        m.rate = f.voteAverage;
        Completable.fromRunnable(() -> dao.addRecord(m)).subscribeOn(Schedulers.io()).subscribe();

    }


}
