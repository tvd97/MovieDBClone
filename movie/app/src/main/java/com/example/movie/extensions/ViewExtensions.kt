package com.example.movie.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movie.R

@BindingAdapter("url")
fun loadImage(img: ImageView, link:String?)
{
    Glide.with(img)
        .load("https://image.tmdb.org/t/p/w300${link}")
        .placeholder(R.drawable.is_loading)
        .into(img)
}
@BindingAdapter("url_mini")
fun loadImageMini(img: ImageView, link:String?)
{
    Glide.with(img)
        .load("https://image.tmdb.org/t/p/w200${link}")
        .placeholder(R.drawable.is_loading_mini)
        .into(img)
}