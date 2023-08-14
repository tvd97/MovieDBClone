package com.example.movie.ui.person

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.remote.people.PeopleRepository
import com.example.movie.model.Person
import com.example.movie.model.Results
import com.example.movie.model.State
import com.example.movie.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(private val repository: PeopleRepository) :
    BaseViewModel() {
    private var _person = MutableLiveData<State<Person>>()
    val person: LiveData<State<Person>>
        get() = _person
    private var list = mutableListOf<Results>()
    private var _movie = MutableLiveData<State<List<Results>>>()
    val movie: LiveData<State<List<Results>>>
        get() = _movie

    fun getPerson(id: Int) {
        launchViewModel(repository.getPerson(id = id), {
            _person.postValue(State.success())
        }, {
            Log.e("err", "$it")
        })
        launchViewModel(repository.getMovieByPerson(id = id), {
            list.addAll(it.results)
            _movie.postValue(State.success(list))
        }, {
            Log.e("err", "$it")
        })

    }
}