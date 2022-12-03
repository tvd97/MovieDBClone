package com.example.moviejava.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonObject {
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public ArrayList<Results> results;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("total_results")
    public int totalResults;

}