package com.example.moviejava.ui.film;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moviejava.R;
import com.example.moviejava.databinding.ActivityFilmBinding;
import com.example.moviejava.model.film.Film;
import com.example.moviejava.ui.base.BaseActivity;
import com.example.moviejava.ui.home.fragment.genres.GenresAdapter;
import com.example.moviejava.ui.person.PersonActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class FilmActivity extends BaseActivity<ActivityFilmBinding> {
    FilmViewModel viewModel;
    @Inject
    CompaniesAdapter companiesAdapter;
    @Inject
    CountriesAdapter countriesAdapter;
    @Inject
    CastAdapter castAdapter;
    @Inject
    CrewAdapter crewAdapter;
    @Inject
    GenresAdapter genresAdapter;


    private Film f;
    String key = "";


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        binding = ActivityFilmBinding.inflate(getLayoutInflater());
//        super.onCreate(savedInstanceState);
//        setSupportActionBar(binding.toolbar);
//        viewModel = new ViewModelProvider(this).get(FilmViewModel.class);
//        Bundle bundle = getIntent().getExtras();
//        viewModel.loadData(bundle.getInt("film_id"));
//        dataOnChange();
//        setView();
//        setEvenAdapter();
//
//        binding.viewTrailer.setOnClickListener(
//                view -> {
//                    Intent intent = new Intent(this, YoutubeActivity.class);
//                    intent.putExtra("video_key", key);
//                    startActivity(intent);
//                }
//        );
//
//        setContentView(binding.getRoot());
//    }

    @Override
    protected ActivityFilmBinding getLayoutBinding() {
        return ActivityFilmBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onHandleObject() {
        viewModel = new ViewModelProvider(this).get(FilmViewModel.class);
        Bundle bundle = getIntent().getExtras();
        viewModel.loadData(bundle.getInt("film_id"));
        setSupportActionBar(binding().toolbar);
        binding().rcvGenres.setAdapter(genresAdapter);
        binding().rcvGenres.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        binding().rcvCast.setAdapter(castAdapter);
        binding().rcvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        binding().rcvCrew.setAdapter(crewAdapter);
        binding().rcvCrew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        binding().rcvCompanies.setAdapter(companiesAdapter);
        binding().rcvCompanies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        binding().rcvCountries.setAdapter(countriesAdapter);
        binding().rcvCountries.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void onHandleEvent() {

        binding().viewTrailer.setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, YoutubeActivity.class);
                    intent.putExtra("video_key", key);
                    startActivity(intent);
                }
        );
        setEvenAdapter();
        defaultObserver(viewModel.film, null, null, null, it -> {
            binding().setF(it);
            binding().executePendingBindings();
            companiesAdapter.submitData(it.productionCompanies);
            countriesAdapter.submitData(it.productionCountries);
            genresAdapter.submitData(it.genres);
        });
        defaultObserver(viewModel.cast, null, null, null, it -> {
            castAdapter.submitData(it.cast);
            crewAdapter.submitData(it.crew);
        });
        defaultObserver(viewModel.video, null, null, null, it -> {
            if (it.results.size() > 0)
                key = it.results.get(0).key;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.add_wish_list: {
                viewModel.addToDb(f);
                Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

//    private void dataOnChange() {
//        viewModel.film.observe(this, it -> {
//            f = it.getData();
//            binding().setF(f);
//            binding().executePendingBindings();
//            companiesAdapter.submitData(f.productionCompanies);
//            countriesAdapter.submitData(f.productionCountries);
//            genresAdapter.submitData(f.genres);
//        });
//        viewModel.cast.observe(this, it -> {
//            castAdapter.submitData(it.cast);
//            crewAdapter.submitData(it.crew);
//        });
//        viewModel.video.observe(this, it ->
//        {
//            if (it.results.size() > 0)
//                key = it.results.get(0).key;
//        });
//        // getSupportFragmentManager().beginTransaction().replace(R.id.youtube_player, new YoutubeFragment().commit());
//    }


    private void setEvenAdapter() {
        castAdapter.onClick = cast -> {
            Intent intent = new Intent(this, PersonActivity.class);
            intent.putExtra("person_id", cast.id);
            startActivity(intent);
        };
        crewAdapter.onClick = crew -> {
            Intent intent = new Intent(this, PersonActivity.class);
            intent.putExtra("person_id", crew.id);
            startActivity(intent);
        };
    }

}