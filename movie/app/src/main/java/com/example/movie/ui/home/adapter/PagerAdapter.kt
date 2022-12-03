package com.example.movie.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.ui.home.fragment.genres.GenresFragment
import com.example.movie.ui.home.fragment.now_playing.NowPlayingFragment
import com.example.movie.ui.home.fragment.popular.PopularFragment
import com.example.movie.ui.home.fragment.top_rate.TopRateFragment
import com.example.movie.ui.home.fragment.upcoming.UpcomingFragment
import javax.inject.Inject

class PagerAdapter @Inject constructor(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularFragment()
            1 -> NowPlayingFragment()
            2 -> UpcomingFragment()
            3 -> TopRateFragment()
            4 -> GenresFragment()
            else -> PopularFragment()
        }
    }


}
