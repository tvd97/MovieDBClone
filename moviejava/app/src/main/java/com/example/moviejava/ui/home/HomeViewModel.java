package com.example.moviejava.ui.home;


import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviejava.data.remote.movie.MovieRepository;
import com.example.moviejava.model.Results;
import com.example.moviejava.model.State;
import com.example.moviejava.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {
    private final MovieRepository repository;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Inject
    public HomeViewModel(MovieRepository repository) {
        this.repository = repository;
        getUpComing(1);
        getPopular(1);
        getNowPlaying(1);
        getTopRate(1);
    }

    private final List<Results> listUpcoming = new ArrayList<>();
    private final MutableLiveData<State<List<Results>>> _upcoming = new MutableLiveData<>();
    public LiveData<State<List<Results>>> upcoming = _upcoming;

    private final List<Results> listTopRate = new ArrayList<>();
    private final MutableLiveData<State<List<Results>>> _topRate = new MutableLiveData<>();
    public LiveData<State<List<Results>>> topRate = _topRate;

    private final List<Results> listPopular = new ArrayList<>();
    private final MutableLiveData<State<List<Results>>> _popular = new MutableLiveData<>();
    public LiveData<State<List<Results>>> popular = _popular;

    private final List<Results> listNowPlaying = new ArrayList<>();
    private final MutableLiveData<State<List<Results>>> _nowPlaying = new MutableLiveData<>();
    public LiveData<State<List<Results>>> nowPlaying = _nowPlaying;
    private List<Results> listSearch = new ArrayList<>();
    private final MutableLiveData<State<List<Results>>> _search = new MutableLiveData<>();
    public LiveData<State<List<Results>>> search = _search;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getUpComing(int page) {
        launchRxSingle(repository.getUpcoming(page), result -> {
            listUpcoming.addAll(result.results);
            _upcoming.postValue(State.success(listUpcoming));
        }, e->android.util.Log.e("ERRR",""+e));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getTopRate(int page) {
        launchRxSingle(repository.getUpcoming(page), result -> {
            listTopRate.addAll(result.results);
            _topRate.postValue(State.success(listTopRate));
        }, e->android.util.Log.e("ERRR",""+e));


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getPopular(int page) {
        launchRxSingle(repository.getUpcoming(page), result -> {
            android.util.Log.e("haiii",""+result);
            listPopular.addAll(result.results);
            _popular.postValue(State.success(listPopular));
        }, e->android.util.Log.e("ERRR",""+e));


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getNowPlaying(int page) {
        launchRxSingle(repository.getUpcoming(page), result -> {
            listNowPlaying.addAll(result.results);
            _nowPlaying.postValue(State.success(listNowPlaying));
        }, e->android.util.Log.e("ERRR",""+e));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void searchMovie(String query, int page, boolean more) {
        launchRxSingle(repository.getUpcoming(page), result -> {
            if (more) {
                listSearch.addAll(result.results);
            }
            listSearch = result.results;

            _search.postValue(State.success(listSearch));
        }, e->android.util.Log.e("ERRR",""+e));
    }
}