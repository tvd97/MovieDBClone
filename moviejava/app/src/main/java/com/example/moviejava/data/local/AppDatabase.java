package com.example.moviejava.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
;import com.example.moviejava.data.local.dao.AppDao;
import com.example.moviejava.data.local.entities.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  public   abstract AppDao appDao();
}