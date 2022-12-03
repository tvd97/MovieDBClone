package com.example.moviejava.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class MovieEntity  {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "adult")
    public boolean adult;
    @ColumnInfo(name = "backdropPath")
    public String backdropPath;
    @ColumnInfo(name = "posterPath")
    public String posterPath;
    @ColumnInfo(name = "releaseDate")
    public String releaseDate;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "rate")
    public double rate;


}