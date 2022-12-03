package com.example.movie.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.databinding.ActivityHomeBinding
import com.example.movie.ui.film.FilmActivity
import com.example.movie.ui.home.adapter.MovieAdapter
import com.example.movie.ui.home.adapter.PagerAdapter
import com.example.movie.ui.favorite.FavoriteActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var adapter: PagerAdapter

    @Inject
    lateinit var movieAdapter: MovieAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(false)
        }
        binding.pageView.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pageView) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Popular"
                }
                1 -> tab.text = "Now playing"
                2 -> tab.text = "Upcoming"
                3 -> tab.text = "Top rate"
                4 -> tab.text = "Genres"

            }
        }.attach()

        viewModel.search.observe(this)
        {
            movieAdapter.submitData(it)
        }
        binding.rcvSearch.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(this@HomeActivity, 4)
        }

        movieAdapter.onClickItem = {
            val intent = Intent(this, FilmActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        searchView = menu?.findItem(R.id.app_bar_search)?.actionView as SearchView
        searchView. apply {
            this.setOnQueryTextListener(
                object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean = false

                    override fun onQueryTextChange(newText: String?): Boolean {
                        binding.ctnSearch.visibility = View.VISIBLE
                        binding.ctnTab.visibility = View.GONE
                        Log.e("text", newText.toString())
                        if (newText != null)
                            viewModel.searchMovie(newText, 1, false)
                        return false
                    }

                })
            this.setOnCloseListener {
                binding.ctnSearch.visibility = View.GONE
                binding.ctnTab.visibility = View.VISIBLE
                false
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.go_to_wish_list)
            true.also {
                startActivity(
                    Intent(
                        this,
                        FavoriteActivity::class.java
                    )
                )
            }
        return super.onOptionsItemSelected(item)
    }

}