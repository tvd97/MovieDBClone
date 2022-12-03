package com.example.moviejava.model.film;

import com.google.gson.annotations.SerializedName;


public class ProductionCompanies {

    @SerializedName("id")
    public int id;
    @SerializedName("logo_path")
    public String logoPath;
    @SerializedName("name")
    public String name;
    @SerializedName("origin_country")
    public String originCountry;


}