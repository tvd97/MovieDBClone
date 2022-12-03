package com.example.movie.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.remote.movie.MovieRepository
import com.example.movie.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    val isLoading = MutableLiveData(true)

    private var listUpcoming = mutableListOf<Results>()
    private val _upcoming = MutableLiveData<List<Results>>()
    val upcoming: LiveData<List<Results>>
        get() = _upcoming

    private var listTopRate = mutableListOf<Results>()
    private val _topRate = MutableLiveData<List<Results>>()
    val topRate: LiveData<List<Results>>
        get() = _topRate

    private var listPopular = mutableListOf<Results>()
    private val _popular = MutableLiveData<List<Results>>()
    val popular: LiveData<List<Results>>
        get() = _popular

    private var listNowPlaying = mutableListOf<Results>()
    private val _nowPlaying = MutableLiveData<List<Results>>()
    val nowPlaying: LiveData<List<Results>>
        get() = _nowPlaying
    private var listSearch = mutableListOf<Results>()
    private val _search = MutableLiveData<List<Results>>()
    val search: LiveData<List<Results>>
        get() = _search

    init {
        getUpComing(page = 1)
        getPopular(page = 1)
        getNowPlaying(page = 1)
        getTopRate(page = 1)

    }

    fun getUpComing(page: Int) {
        viewModelScope.launch {
            repository.getUpcoming(page = page)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    listUpcoming.addAll(data.results)
                    _upcoming.value = listUpcoming
                    isLoading.value = false
                }
        }
    }

    fun getTopRate(page: Int) {
        viewModelScope.launch {
            repository.getTopRate(page = page)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    listTopRate.addAll(data.results)
                    _topRate.value = listTopRate
                    isLoading.value = false
                }
        }
    }

    fun getPopular(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPopular(page = page)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    withContext(Dispatchers.Main)
                    {
                        listPopular.addAll(data.results)
                        _popular.value = listPopular
                        isLoading.value = false
                    }

                }
        }
    }

    fun getNowPlaying(page: Int) {
        viewModelScope.launch {
            repository.getNowPlaying(page = page)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    listNowPlaying.addAll(data.results)
                    _nowPlaying.value = listNowPlaying
                    isLoading.value = false
                }
        }
    }

    fun searchMovie(query: String, page: Int, more: Boolean) {
        viewModelScope.launch {
            repository.searchMovie(query = query, page = page)
                .catch {
                    Log.e("err", "err")
                }
                .collect { data ->
                    if (more) listSearch.addAll(data.results)
                    else listSearch = data.results

                    _search.value = listSearch
                }
        }

    }
}