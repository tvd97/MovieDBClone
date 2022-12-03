package com.example.movie.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.local.dao.AppDao
import com.example.movie.data.local.entities.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val dao: AppDao) : ViewModel() {
    //private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>>
        get() = dao.selectRecord()

    fun  deleteRecord(m:MovieEntity)
    {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteRecord(m)
        }
    }
}