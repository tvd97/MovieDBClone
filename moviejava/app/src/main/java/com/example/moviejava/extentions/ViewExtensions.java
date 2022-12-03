package com.example.moviejava.extentions;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.example.moviejava.R;

import kotlin.jvm.JvmOverloads;


public class ViewExtensions {
    @JvmOverloads
    @BindingAdapter("url")
    public static void loadImage(ImageView img, String link) {
        Glide.with(img)
                .load("https://image.tmdb.org/t/p/w300"+link)
                .placeholder(R.drawable.is_loading)
                .into(img);
    }
    @JvmOverloads
    @BindingAdapter("url_mini")
    public static void loadImageMini(ImageView img, String link) {
        Glide.with(img)
                .load("https://image.tmdb.org/t/p/w200"+link)
                .placeholder(R.drawable.is_loading_mini)
                .into(img);
    }
}