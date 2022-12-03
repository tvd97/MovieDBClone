package com.example.moviejava.model.up_coming;


import com.example.moviejava.model.Dates;
import com.example.moviejava.model.Results;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpComing {

    @SerializedName("dates")
    public Dates dates;
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public ArrayList<Results> results;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("total_results")
    public int totalResult;

}