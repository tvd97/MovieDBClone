package com.example.movie.ui.person

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.remote.people.PeopleRepository
import com.example.movie.model.Person
import com.example.movie.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(private val repository: PeopleRepository) : ViewModel() {
    private var _person = MutableLiveData<Person>()
    val person: LiveData<Person>
        get() = _person
    private var list = mutableListOf<Results>()
    private var _movie = MutableLiveData<List<Results>>()
    val movie: LiveData<List<Results>>
        get() = _movie

    fun getPerson(id: Int) {
        viewModelScope.launch {
            repository.getPerson(id = id).catch {
                Log.e("err", "err")
            }
                .collect { data ->
                    _person.value = data
                }
        }
        viewModelScope.launch {
            repository.getMovieByPerson(id = id).catch {
                Log.e("err", "err")
            }
                .collect { data ->
                    list.addAll(data.results)
                    _movie.value = list
                }
        }

    }
}