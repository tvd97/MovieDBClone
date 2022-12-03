package com.example.movie.model.film.video

import com.google.gson.annotations.SerializedName


data class Video(

    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("results")
    var results: ArrayList<Results> = arrayListOf()

)
