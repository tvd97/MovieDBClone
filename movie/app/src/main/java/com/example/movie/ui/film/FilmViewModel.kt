package com.example.movie.ui.film

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.local.dao.AppDao
import com.example.movie.data.local.entities.MovieEntity
import com.example.movie.data.remote.film.FilmRepository
import com.example.movie.model.State
import com.example.movie.model.casts.CastsFilm
import com.example.movie.model.film.Film
import com.example.movie.model.film.video.Video
import com.example.movie.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val repository: FilmRepository, private val dao: AppDao
) : BaseViewModel() {
    private val _film = MutableLiveData<State<Film>>()
    val film: MutableLiveData<State<Film>>
        get() = _film

    private val _video = MutableLiveData<State<Video>>()
    val video: LiveData<State<Video>>
        get() = _video
    private val _casts = MutableLiveData<State<CastsFilm>>()
    val casts: LiveData<State<CastsFilm>>
        get() = _casts

    fun loadData(id: Int) {
        launchViewModel(repository.getFilm(id), {
            _film.postValue(State.success())
        }, {
            Log.e("err", it.message.toString())
        })

        launchViewModel(repository.getVideo(id), {
            _video.postValue(State.success(it))
        }, {
            Log.e("err", it.message.toString())
        })

        launchViewModel(repository.getCastFilm(id), {
            _casts.postValue(State.success(it))
        }, {
            Log.e("err", it.message.toString())
        })
    }

    fun addToDb(r: Film) {
        val m = MovieEntity(
            id = r.id,
            adult = r.adult,
            backdropPath = r.backdropPath,
            posterPath = r.posterPath,
            releaseDate = r.releaseDate,
            title = r.title,
            rate = r.voteAverage
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.addRecord(m)
        }
    }

}