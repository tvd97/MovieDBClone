package com.example.movie.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.remote.movie.MovieRepository
import com.example.movie.model.Results
import com.example.movie.model.State
import com.example.movie.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : BaseViewModel() {

    private var listUpcoming = mutableListOf<Results>()
    private val _upcoming = MutableLiveData<State<List<Results>>>()
    val upcoming: LiveData<State<List<Results>>>
        get() = _upcoming

    private var listTopRate = mutableListOf<Results>()
    private val _topRate = MutableLiveData<State<List<Results>>>()
    val topRate: LiveData<State<List<Results>>>
        get() = _topRate

    private var listPopular = mutableListOf<Results>()
    private val _popular = MutableLiveData<State<List<Results>>>()
    val popular: LiveData<State<List<Results>>>
        get() = _popular

    private var listNowPlaying = mutableListOf<Results>()
    private val _nowPlaying = MutableLiveData<State<List<Results>>>()
    val nowPlaying: LiveData<State<List<Results>>>
        get() = _nowPlaying
    private var listSearch = mutableListOf<Results>()
    private val _search = MutableLiveData<State<List<Results>>>()
    val search: LiveData<State<List<Results>>>
        get() = _search

    init {
        getUpComing(page = 1)
        getPopular(page = 1)
        getNowPlaying(page = 1)
        getTopRate(page = 1)

    }

    fun getUpComing(page: Int) {
        _upcoming.postValue(State.loading())
        launchViewModel(repository.getUpcoming(page = page), {
            listUpcoming.addAll(it.results)
            _upcoming.postValue(State.success(listUpcoming))
        }, {
            Log.e("err", it.message.toString())
        })
    }

    fun getTopRate(page: Int) {
        _topRate.postValue(State.loading())
        launchViewModel(repository.getTopRate(page = page), {
            listTopRate.addAll(it.results)
            _topRate.postValue(State.success(listTopRate))
        }, {
            Log.e("err", it.message.toString())
        })
    }

    fun getPopular(page: Int) {
        _popular.postValue(State.loading())
        launchViewModel(repository.getPopular(page = page), {
            listPopular.addAll(it.results)
            _popular.postValue(State.success(listPopular))
        }, {
            Log.e("err", it.message.toString())
        })


    }

    fun getNowPlaying(page: Int) {
        _nowPlaying.postValue(State.loading())
        launchViewModel(repository.getNowPlaying(page = page), {
            listNowPlaying.addAll(it.results)
            _nowPlaying.postValue(State.success(listNowPlaying))
        }, {
            Log.e("err", it.message.toString())
        })

    }

    fun searchMovie(query: String, page: Int, more: Boolean) {

        launchViewModel(repository.searchMovie(query = query, page = page), {
            if (more) listSearch.addAll(it.results)
            else listSearch = it.results
            _search.postValue(State.success(listSearch))
        }, {
            Log.e("err", it.message.toString())
        })


    }
}