package com.example.moviejava.ui.person;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.moviejava.R;
import com.example.moviejava.databinding.ActivityPersonBinding;
import com.example.moviejava.ui.base.BaseActivity;
import com.example.moviejava.ui.home.adapter.MovieAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PersonActivity extends BaseActivity<ActivityPersonBinding> {


    private PersonViewModel viewModel;
    @Inject
    MovieAdapter movieAdapter;


    @Override
    protected ActivityPersonBinding getLayoutBinding() {
        return ActivityPersonBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onHandleObject() {
        setSupportActionBar(binding().toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        viewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("person_id");
        viewModel.getData(id);
                binding().showMore.setShowingLine(4);
        binding().showMore.setShowLessTextColor(Color.parseColor("#FF42E2F6"));
        binding().showMore.setShowMoreTextColor(Color.parseColor("#FF42E2F6"));
        binding().showMore.addShowLessText("hide");
        binding().showMore.addShowMoreText("read more");
    }

    @Override
    protected void onHandleEvent() {
        defaultObserver(viewModel.person, null, null, null, it -> {

            binding().setP(it);
            binding().executePendingBindings();
        });
        defaultObserver(viewModel.movie, null, null, null, it -> movieAdapter.submitData(it));

        binding().rcvMovie.setAdapter(movieAdapter);
        binding().rcvMovie.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}