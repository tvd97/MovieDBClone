package com.example.moviejava.model.casts;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class CastsFilm {

    @SerializedName("id")
    public int id;
    @SerializedName("cast")
    public ArrayList<Cast> cast;
    @SerializedName("crew")
    public ArrayList<Crew> crew;

}