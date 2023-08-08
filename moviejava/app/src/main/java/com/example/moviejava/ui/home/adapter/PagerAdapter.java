package com.example.moviejava.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.moviejava.R;
import com.example.moviejava.ui.home.fragment.genres.GenresFragment;
import com.example.moviejava.ui.home.fragment.now_playing.NowPlayingFragment;
import com.example.moviejava.ui.home.fragment.popular.PopularFragment;
import com.example.moviejava.ui.home.fragment.top_rate.TopRateFragment;
import com.example.moviejava.ui.home.fragment.upcoming.UpcomingFragment;

import javax.inject.Inject;


public class PagerAdapter extends
        FragmentStateAdapter {
    @Inject
    public PagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new NowPlayingFragment(R.layout.fragment_now_playing);
            case 2:
                return new UpcomingFragment(R.layout.fragment_upcoming);
            case 3:
                return new TopRateFragment(R.layout.fragment_top_rate);
            case 4:
                return new GenresFragment(R.layout.fragment_genres);
            default:
                return new PopularFragment(R.layout.fragment_popular);
        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }
}
