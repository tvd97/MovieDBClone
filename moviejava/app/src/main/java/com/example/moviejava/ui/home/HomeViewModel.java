package com.example.moviejava.ui.home;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviejava.data.remote.movie.MovieRepository;
import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.Results;
import com.example.moviejava.model.up_coming.UpComing;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.inject.Inject;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final MovieRepository repository;

    @Inject
    public HomeViewModel(MovieRepository repository) {
        this.repository = repository;
        getUpComing(1);
        getPopular(1);
        getNowPlaying(1);
        getTopRate(1);
    }

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(true);
    public LiveData<Boolean> isLoading = _isLoading;
    private final List<Results> listUpcoming = new ArrayList<>();
    private final MutableLiveData<List<Results>> _upcoming = new MutableLiveData<>();
    public LiveData<List<Results>> upcoming = _upcoming;

    private final List<Results> listTopRate = new ArrayList<>();
    private final MutableLiveData<List<Results>> _topRate = new MutableLiveData<>();
    public LiveData<List<Results>> topRate = _topRate;

    private final List<Results> listPopular = new ArrayList<>();
    private final MutableLiveData<List<Results>> _popular = new MutableLiveData<>();
    public LiveData<List<Results>> popular = _popular;

    private final List<Results> listNowPlaying = new ArrayList<>();
    private final MutableLiveData<List<Results>> _nowPlaying = new MutableLiveData<>();
    public LiveData<List<Results>> nowPlaying = _nowPlaying;
    private List<Results> listSearch = new ArrayList<>();
    private final MutableLiveData<List<Results>> _search = new MutableLiveData<>();
    public LiveData<List<Results>> search = _search;


    public void getUpComing(int page) {

        repository.getUpcoming(page).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpComing>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpComing upComing) {
                        listUpcoming.addAll(upComing.results);
                        _upcoming.setValue(listUpcoming);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        _isLoading.setValue(false);
                        Log.d("success", "call api success");
                    }
                });

    }

    public void getTopRate(int page) {
        repository.getTopRate(page).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonObject jsonObject) {
                        listTopRate.addAll(jsonObject.results);
                        _topRate.setValue(listTopRate);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        _isLoading.setValue(false);
                        Log.d("success", "call api success");
                    }
                });

    }

    public void getPopular(int page) {
        repository.getPopular(page).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonObject jsonObject) {
                        listPopular.addAll(jsonObject.results);
                        _popular.setValue(listPopular);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        _isLoading.setValue(false);
                        Log.d("success", "call api success");
                    }
                });

    }

    public void getNowPlaying(int page) {
        repository.getNowPlaying(page).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpComing>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpComing upComing) {
                        listNowPlaying.addAll(upComing.results);
                        _nowPlaying.setValue(listNowPlaying);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        _isLoading.setValue(false);
                        Log.d("success", "call api success");
                    }
                });

    }

    public void searchMovie(String query, int page, boolean more) {
        repository.searchMovie(query, page).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonObject jsonObject) {
                        if (more) {
                            listSearch.addAll(jsonObject.results);
                        }
                        listSearch = jsonObject.results;

                        _search.setValue(listSearch);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("success", "call api success");
                    }
                });

    }
}