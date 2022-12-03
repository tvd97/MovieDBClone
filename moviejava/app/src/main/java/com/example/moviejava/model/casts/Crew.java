package com.example.moviejava.model.casts;

import com.google.gson.annotations.SerializedName;


public class Crew {

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
    @SerializedName("credit_id")
    public String creditId;
    @SerializedName("department")
    public String department;
    @SerializedName("job")
    public String job;

}