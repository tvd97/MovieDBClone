package com.example.moviejava.model.film.video;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Video {

    @SerializedName("id")
    public int id;
    @SerializedName("results")
    public ArrayList<Results> results;

}
