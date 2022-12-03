package com.example.moviejava.ui.home.fragment.genres;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviejava.data.remote.genres.GenresRepository;
import com.example.moviejava.model.Genres;
import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.Results;
import com.example.moviejava.model.genres.FromJson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class GenresViewModel extends ViewModel {

    private GenresRepository repository;

    private final MutableLiveData<List<Genres>> _genres = new MutableLiveData<>();

    public LiveData<List<Genres>> getGenres() {
        return _genres;
    }

    private final ArrayList<Results> list = new ArrayList<>();
    private final MutableLiveData<List<Results>> _results = new MutableLiveData<>();

    public LiveData<List<Results>> getResults() {
        return _results;
    }

    private final MutableLiveData<Boolean> _isChose = new MutableLiveData<>(false);

    public LiveData<Boolean> getChose() {
        return _isChose;
    }

    public void clearData() {
        list.clear();
    }

    @Inject
    public GenresViewModel(GenresRepository repository) {
        this.repository = repository;
        setGenres();
    }

    public void setGenres() {
        repository.getGenres()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FromJson>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull FromJson fromJson) {
                        _genres.setValue(fromJson.genres);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        try {
                            throw new Exception();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.e("hey hey hey", "call api success");
                    }
                });


    }

    public void setResults(int id, int page) {

        repository.getListGenresByFilm(id, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonObject jsonObject) {
                        list.addAll(jsonObject.results);
                        _results.setValue(list);
                        _isChose.setValue(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.e("ok", "good");
                    }
                });

    }

}
