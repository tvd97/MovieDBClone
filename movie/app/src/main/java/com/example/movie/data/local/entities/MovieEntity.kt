package com.example.movie.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "adult")
    var adult: Boolean? = null,
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null,
    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "rate")
    var rate: Double? = null,
) : Parcelable