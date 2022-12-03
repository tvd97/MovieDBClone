package com.example.movie.ui.person

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.databinding.ActivityPersonBinding
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonBinding

    private val viewModel: PersonViewModel by viewModels()
    @Inject
    lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        val bundle: Bundle? = intent.extras
        val id = bundle!!.getInt("person_id")
        viewModel.getPerson(id)
        viewModel.apply {
            person.observe(this@PersonActivity)
            {
                binding.p=it
                binding.executePendingBindings()
            }
            movie.observe(this@PersonActivity)
            {
                movieAdapter.submitData(it)
            }
        }
        binding.rcvMovie.apply {
            adapter=movieAdapter
            layoutManager= GridLayoutManager(this@PersonActivity,4)
        }
        binding.showMore.apply {
            setShowingLine(4)
            setShowLessTextColor(Color.parseColor("#FF42E2F6"))
            setShowMoreTextColor(Color.parseColor("#FF42E2F6"))
            addShowLessText("hide")
            addShowMoreText("read more")

        }
        setContentView(binding.root)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> true.also { finish() }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)

    }
}