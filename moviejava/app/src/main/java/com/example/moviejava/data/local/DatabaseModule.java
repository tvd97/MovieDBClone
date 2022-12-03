package com.example.moviejava.data.local;


import android.app.Application;

import androidx.room.Room;

import com.example.moviejava.data.local.dao.AppDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)

public class DatabaseModule {

    @Singleton
    @Provides
    public AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(
                        application.getApplicationContext(),
                        AppDatabase.class,
                        "app_db.db"
                )
                .build();
    }

    @Provides
    @Singleton
    public AppDao provideAppDao(AppDatabase db) {
        return db.appDao();
    }
}