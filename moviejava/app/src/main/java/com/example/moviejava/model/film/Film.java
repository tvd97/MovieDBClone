package com.example.moviejava.model.film;


import com.example.moviejava.model.Genres;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.ArrayList;

public class Film {

    @SerializedName("adult")
    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    public BelongsToCollection belongsToCollection;

    @SerializedName("budget")
    public int budget;
    @SerializedName("genres")
    public ArrayList<Genres> genres;

    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public int id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("production_companies")
    public ArrayList<ProductionCompanies> productionCompanies;

    @SerializedName("production_countries")
    public ArrayList<ProductionCountries> productionCountries;

    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("revenue")
    public BigInteger revenue;
    @SerializedName("runtime")
    public int runtime;
    @SerializedName("spoken_languages")
    public ArrayList<SpokenLanguages> spokenLanguages;

    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagline;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public double voteAverage;
    @SerializedName("vote_count")
    public int voteCount;


}


