package com.example.moviejava.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moviejava.R;
import com.example.moviejava.databinding.ActivityFavoriteBinding;
import com.example.moviejava.ui.film.FilmActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class FavoriteActivity extends AppCompatActivity {
    private ActivityFavoriteBinding binding;
    private FavoriteViewModel viewModel;
    @Inject
    FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        viewModel.movies.observe(this, it -> {
            Log.e("counttt",String.valueOf(it.size()));
            favoriteAdapter.submitData(it);
        });
        binding.rcvFavorite.setAdapter(favoriteAdapter);
        binding.rcvFavorite.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        setEventAdapter();

        setContentView(binding.getRoot());
    }
    private  void setEventAdapter()
    {
        favoriteAdapter.onClick=movie -> {
            Intent intent= new Intent(this, FilmActivity.class);
            intent.putExtra("film_id",movie.id);
            startActivity(intent);
        };
        favoriteAdapter.onDelete=m->{
            viewModel.deleteRecord(m);
        };
    }

}