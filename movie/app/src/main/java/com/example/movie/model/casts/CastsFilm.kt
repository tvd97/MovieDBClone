package com.example.movie.model.casts

import com.google.gson.annotations.SerializedName


data class CastsFilm
    (

    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("cast")
    var cast: ArrayList<Cast> = arrayListOf(),
    @SerializedName("crew")
    var crew: ArrayList<Crew> = arrayListOf()

)