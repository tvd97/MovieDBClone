package com.example.movie.ui.home.fragment.genres

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.remote.genres.GenresRepository
import com.example.movie.model.Genres
import com.example.movie.model.Results
import com.example.movie.model.State
import com.example.movie.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(val repository: GenresRepository) : BaseViewModel() {
    private val _genres = MutableLiveData<State<List<Genres>>>()
    val listGenres: LiveData<State<List<Genres>>>
        get() = _genres
    private val list = mutableListOf<Results>()
    private val _results = MutableLiveData<State<List<Results>>>()
    val results: LiveData<State<List<Results>>>
        get() = _results
    private val _isChose = MutableLiveData(false)
    val isChose: LiveData<Boolean>
        get() = _isChose

    fun clearData() {
        list.clear()
    }

    init {
        launchViewModel(repository.getGenres(), { _genres.postValue(State.success(it.genres)) }, {
            Log.e("err", it.message.toString())
        })

    }

    fun getListFilmByGenres(id: Int, page: Int) {
        _results.postValue(State.loading())
        launchViewModel(repository.getListFilmByGenres(id = id, page = page), {
            list.addAll(it.results)
            _results.postValue(State.success(list))
            _isChose.value = true
        }, {
            Log.e("err", it.message.toString())
        })
    }
}