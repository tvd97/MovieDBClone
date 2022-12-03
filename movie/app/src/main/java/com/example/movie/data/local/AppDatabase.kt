package com.example.movie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movie.data.local.dao.AppDao
import com.example.movie.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}