package com.example.moviejava.data.local.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.moviejava.data.local.entities.MovieEntity;

import java.util.List;

@Dao
public interface AppDao {
    @Insert(onConflict = REPLACE)
    void addRecord(MovieEntity m);

    @Delete
    void deleteRecord(MovieEntity m);

    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntity>> selectRecord();
}