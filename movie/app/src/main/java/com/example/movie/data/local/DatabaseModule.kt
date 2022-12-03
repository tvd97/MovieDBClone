package com.example.movie.data.local

import android.app.Application
import androidx.room.Room
import com.example.movie.data.local.dao.AppDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "app_db.db"
        )
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideAppDao(db: AppDatabase): AppDao = db.appDao()
}