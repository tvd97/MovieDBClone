package com.example.moviejava.model.film.video;

import com.google.gson.annotations.SerializedName;


public class Results {

    @SerializedName("iso_639_1")
    public String iso6391;
    @SerializedName("iso_3166_1")
    public String iso31661;
    @SerializedName("name")
    public String name;
    @SerializedName("key")
    public String key;
    @SerializedName("site")
    public String site;
    @SerializedName("size")
    public int size;
    @SerializedName("type")
    public String type;
    @SerializedName("official")
    public boolean official;
    @SerializedName("published_at")
    public String publishedAt;
    @SerializedName("id")
    public String id;

}