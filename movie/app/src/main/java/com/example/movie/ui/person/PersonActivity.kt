package com.example.movie.ui.person

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.databinding.ActivityPersonBinding
import com.example.movie.ui.base.BaseActivity
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PersonActivity : BaseActivity<ActivityPersonBinding>() {


    private val viewModel: PersonViewModel by viewModels()

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun getLayoutBinding(): ActivityPersonBinding =
        ActivityPersonBinding.inflate(layoutInflater)

    override fun onHandleObject() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        val bundle: Bundle? = intent.extras
        val id = bundle!!.getInt("person_id")
        viewModel.getPerson(id)
        binding.rcvMovie.apply {
            adapter=movieAdapter
            layoutManager = GridLayoutManager(this@PersonActivity, 4)
        }
        binding.showMore.apply {
            setShowingLine(4)
            setShowLessTextColor(Color.parseColor("#FF42E2F6"))
            setShowMoreTextColor(Color.parseColor("#FF42E2F6"))
            addShowLessText("hide")
            addShowMoreText("read more")

        }
    }

    override fun onHandleEvent() {
        viewModel.apply {
            defaultObserver(person, null, null) {
                binding.p = it
                binding.executePendingBindings()
            }
            defaultObserver(movie, null, null) {

                it?.let { movieAdapter.submitData(it) }

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> true.also { finish() }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)

    }
}