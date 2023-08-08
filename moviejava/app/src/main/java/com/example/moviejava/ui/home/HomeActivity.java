package com.example.moviejava.ui.home;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.moviejava.R;
import com.example.moviejava.databinding.ActivityHomeBinding;
import com.example.moviejava.ui.base.BaseActivity;
import com.example.moviejava.ui.favorite.FavoriteActivity;
import com.example.moviejava.ui.home.adapter.MovieAdapter;
import com.example.moviejava.ui.home.adapter.PagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    @Inject
    PagerAdapter pagerAdapter;
    @Inject
    MovieAdapter movieAdapter;

    private HomeViewModel viewModel;

    @Override
    protected ActivityHomeBinding getLayoutBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onHandleObject() {
        setSupportActionBar(binding().toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        binding().pageView.setAdapter(pagerAdapter);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        new TabLayoutMediator(binding().tabLayout, binding().pageView, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Popular");
                    break;
                case 1:
                    tab.setText("Now Playing");
                    break;
                case 2:
                    tab.setText("Upcoming");
                    break;
                case 3:
                    tab.setText("Top Rate");
                    break;
                case 4:
                    tab.setText("Genres");
                    break;
            }
        }).attach();
    }

    @Override
    protected void onHandleEvent() {
        defaultObserver(viewModel.search,null,null,null,it->movieAdapter.submitData(it));
        binding().rcvSearch.setAdapter(movieAdapter);
        binding().rcvSearch.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
//       // searchView.setSearchableInfo(((SearchManager) getSystemService(Context.SEARCH_SERVICE)).getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                binding().ctnSearch.setVisibility(View.VISIBLE);
                binding().ctnTab.setVisibility(View.GONE);
                viewModel.searchMovie(s, 1, false);
                return false;
            }
        });
        searchView.setOnCloseListener(() -> {
            binding().ctnSearch.setVisibility(View.GONE);
            binding().ctnTab.setVisibility(View.VISIBLE);
            return false;
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.go_to_wish_list) {
            startActivity(
                    new Intent(this, FavoriteActivity.class
                    )
            );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
