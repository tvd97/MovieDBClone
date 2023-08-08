package com.example.moviejava.ui.favorite;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviejava.data.local.dao.AppDao;
import com.example.moviejava.data.local.entities.MovieEntity;
import com.example.moviejava.ui.base.BaseViewModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class FavoriteViewModel extends BaseViewModel {
    private AppDao dao;

    @Inject
    public FavoriteViewModel(@NonNull AppDao dao) {
        this.dao = dao;
        this.movies= dao.selectRecord();
    }


    public LiveData<List<MovieEntity>> movies;

    public void deleteRecord(MovieEntity m) {

        Completable.fromRunnable(() -> dao.deleteRecord(m)).subscribeOn(Schedulers.io()).subscribe();
    }
}