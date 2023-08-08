package com.example.moviejava.ui.home.fragment.genres;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviejava.data.remote.genres.GenresRepository;
import com.example.moviejava.model.Genres;
import com.example.moviejava.model.Results;
import com.example.moviejava.model.State;
import com.example.moviejava.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GenresViewModel extends BaseViewModel {

    private GenresRepository repository;

    private final MutableLiveData<State<List<Genres>>> _genres = new MutableLiveData<>();

    public LiveData<State<List<Genres>>> getGenres() {
        return _genres;
    }

    private final ArrayList<Results> list = new ArrayList<>();
    private final MutableLiveData<State<List<Results>>> _results = new MutableLiveData<>();

    public LiveData<State<List<Results>>> getResults() {
        return _results;
    }

    private final MutableLiveData<State<Boolean>> _isChose = new MutableLiveData<>();

    public LiveData<State<Boolean>> getChose() {
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
        launchRxSingle(repository.getGenres(), result -> _genres.postValue(State.success(result.genres)), null);

    }

    public void setResults(int id, int page) {
        launchRxSingle(repository.getListGenresByFilm(id, page), result -> {
            list.addAll(result.results);
            _results.postValue(State.success(list));
            _isChose.postValue(State.success(true));
        }, null);
    }

}
