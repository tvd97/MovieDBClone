package com.example.moviejava.ui.person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviejava.data.remote.people.PeopleRepository;
import com.example.moviejava.model.JsonObject;
import com.example.moviejava.model.Person;
import com.example.moviejava.model.Results;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class PersonViewModel extends ViewModel {
    private final PeopleRepository repository;

    @Inject
    public PersonViewModel(PeopleRepository repository) {
        this.repository = repository;
    }

    private final MutableLiveData<Person> _person = new MutableLiveData<>();
    public LiveData<Person> person = _person;

    private final List<Results> list = new ArrayList<>();

    private final MutableLiveData<List<Results>> _movie = new MutableLiveData<>();
    public LiveData<List<Results>> movie = _movie;

    public void getData(int id) {
        repository.getPerson(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Person>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Person person) {
                        _person.setValue(person);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        repository.getMovieByPerson(id).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonObject jsonObject) {
                        list.addAll(jsonObject.results);
                        _movie.setValue(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
