package com.example.movie.ui.film

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.local.dao.AppDao
import com.example.movie.data.local.entities.MovieEntity
import com.example.movie.data.remote.film.FilmRepository
import com.example.movie.model.Results
import com.example.movie.model.casts.CastsFilm
import com.example.movie.model.film.Film
import com.example.movie.model.film.video.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val repository: FilmRepository, private val dao: AppDao
) : ViewModel() {
    private val _film = MutableLiveData<Film>()
    val film: LiveData<Film>
        get() = _film

    private val _video = MutableLiveData<Video>()
    val video: LiveData<Video>
        get() = _video
    private val _casts = MutableLiveData<CastsFilm>()
    val casts: LiveData<CastsFilm>
        get() = _casts

    fun loadData(id: Int) {
        viewModelScope.launch() {
            repository.getFilm(id)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    _film.value = data
                }
            repository.getVideo(id)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    _video.value = data
                }
            repository.getCastFilm(id)
                .catch { e ->
                    Log.e("err", e.message.toString())
                }
                .collect { data ->
                    _casts.value = data
                }
        }
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