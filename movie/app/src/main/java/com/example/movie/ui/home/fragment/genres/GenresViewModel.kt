package com.example.movie.ui.home.fragment.genres

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.remote.genres.GenresRepository
import com.example.movie.model.Results
import com.example.movie.model.Genres
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(val repository: GenresRepository) : ViewModel() {
    private val _genres = MutableLiveData<List<Genres>>()
    val listGenres: LiveData<List<Genres>>
        get() = _genres
    private val list = mutableListOf<Results>()
    private val _results = MutableLiveData<List<Results>>()
    val results: LiveData<List<Results>>
        get() = _results
    private val _isChose = MutableLiveData(false)
    val isChose: LiveData<Boolean>
        get() = _isChose

    fun clearData() {
        list.clear()
    }

    init {
        viewModelScope.launch {
            repository.getGenres()
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    _genres.value = data.genres
                }
        }

    }

    fun getListFilmByGenres(id: Int, page: Int) {
        viewModelScope.launch {
            repository.getListFilmByGenres(id = id, page = page)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    list.addAll(data.results)
                    _results.value = list
                    _isChose.value = true
                }
        }

    }
}