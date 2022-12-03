package com.example.moviejava.model.casts;

import com.google.gson.annotations.SerializedName;


public class Cast {

    @SerializedName("adult")
    public boolean adult;

    @SerializedName("gender")
    public int gender;

    @SerializedName("id")
    public int id;

    @SerializedName("known_for_department")
    public String knownForDepartment;

    @SerializedName("name")
    public String name;

    @SerializedName("original_name")
    public String originalName;

    @SerializedName("popularity")
    public double popularity;

    @SerializedName("profile_path")
    public String profilePath;

    @SerializedName("cast_id")
    public int castId;

    @SerializedName("character")
    public String character;

    @SerializedName("credit_id")
    public String creditId;

    @SerializedName("order")
    public int order;

}