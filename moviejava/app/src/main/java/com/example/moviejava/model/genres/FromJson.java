package com.example.moviejava.model.genres;


import com.example.moviejava.model.Genres;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FromJson {

    @SerializedName("genres")
   public ArrayList<Genres> genres;

}