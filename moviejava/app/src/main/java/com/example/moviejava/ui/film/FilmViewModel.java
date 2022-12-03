package com.example.moviejava.ui.film;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviejava.data.local.dao.AppDao;
import com.example.moviejava.data.local.entities.MovieEntity;
import com.example.moviejava.data.remote.film.FilmRepository;
import com.example.moviejava.model.casts.CastsFilm;
import com.example.moviejava.model.film.Film;
import com.example.moviejava.model.film.video.Video;

import java.util.Observable;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;

@HiltViewModel
public class FilmViewModel extends ViewModel {
    private FilmRepository repository;
    private AppDao dao;

    @Inject
    public FilmViewModel(FilmRepository repository, AppDao dao) {
        this.repository = repository;
        this.dao = dao;

    }

    private MutableLiveData<Film> _film = new MutableLiveData<>();
    public LiveData<Film> film = _film;

    private MutableLiveData<Video> _video = new MutableLiveData<>();
    public LiveData<Video> video = _video;

    private MutableLiveData<CastsFilm> _cast = new MutableLiveData<>();
    public LiveData<CastsFilm> cast = _cast;

    public void loadData(int id) {
        repository.getFilm(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Film>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Film film) {
                        _film.setValue(film);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("complete", "not error");
                    }
                });
        repository.getVideo(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Video>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Video video) {
                        _video.setValue(video);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("complete", "not error");
                    }
                });
        repository.getCast(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CastsFilm>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CastsFilm castsFilm) {
                        _cast.setValue(castsFilm);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("complete", "not error");
                    }
                });
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
