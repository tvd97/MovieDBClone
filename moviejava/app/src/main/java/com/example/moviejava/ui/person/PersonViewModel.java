package com.example.moviejava.ui.person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviejava.data.remote.people.PeopleRepository;
import com.example.moviejava.model.Person;
import com.example.moviejava.model.Results;
import com.example.moviejava.model.State;
import com.example.moviejava.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PersonViewModel extends BaseViewModel {
    private final PeopleRepository repository;

    @Inject
    public PersonViewModel(PeopleRepository repository) {
        this.repository = repository;
    }

    private final MutableLiveData<State<Person>> _person = new MutableLiveData<>();
    public LiveData<State<Person>> person = _person;

    private final List<Results> list = new ArrayList<>();

    private final MutableLiveData<State<List<Results>>> _movie = new MutableLiveData<>();
    public LiveData<State<List<Results>>> movie = _movie;

    public void getData(int id) {
        launchRxSingle(repository.getPerson(id),result->_person.postValue(State.success(result)),null);

        launchRxSingle(repository.getMovieByPerson(id),result->{
            list.addAll(result.results);
            _movie.postValue(State.success(list));
        },null);
    }
}
