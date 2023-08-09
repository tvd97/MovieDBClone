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
                return new NowPlayingFragment();
            case 2:
                return new UpcomingFragment();
            case 3:
                return new TopRateFragment();
            case 4:
                return new GenresFragment();
            default:
                return new PopularFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }
}
