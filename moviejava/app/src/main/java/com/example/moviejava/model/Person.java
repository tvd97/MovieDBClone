package com.example.moviejava.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Person {
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("also_known_as")
    public ArrayList<String> alsoKnownAs;
    @SerializedName("biography")
    public String biography;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("deathday")
    public String deathday;
    @SerializedName("gender")
    public int gender;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public int id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("known_for_department")
    public String knownForDepartment;
    @SerializedName("name")
    public String name;
    @SerializedName("place_of_birth")
    public String placeOfBirth;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("profile_path")
    public String profilePath;
}
