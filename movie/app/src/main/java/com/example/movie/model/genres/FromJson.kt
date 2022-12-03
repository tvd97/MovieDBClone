package com.example.movie.model.genres

import com.example.movie.model.Genres
import com.google.gson.annotations.SerializedName
data class FromJson(

    @SerializedName("genres")
    var genres: ArrayList<Genres> = arrayListOf()

)