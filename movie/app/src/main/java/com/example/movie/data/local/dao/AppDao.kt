package com.example.movie.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.movie.data.local.entities.MovieEntity

@Dao
interface AppDao {
    @Insert(onConflict = REPLACE)
    suspend fun addRecord(m: MovieEntity)

    @Delete
    suspend fun deleteRecord(m: MovieEntity)

    @Query("SELECT * FROM movie")
    fun selectRecord(): LiveData<List<MovieEntity>>
}