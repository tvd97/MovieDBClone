package com.example.movie.ui.film

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.databinding.ActivityFilmBinding
import com.example.movie.model.film.Film
import com.example.movie.ui.home.fragment.genres.GenresAdapter
import com.example.movie.ui.person.PersonActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Suppress("CAST_NEVER_SUCCEEDS")
@AndroidEntryPoint
class FilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmBinding

    @Inject
    lateinit var countriesAdapter: CountriesAdapter

    @Inject
    lateinit var companiesAdapter: CompaniesAdapter

    @Inject
    lateinit var castAdapter: CastAdapter

    @Inject
    lateinit var crewAdapter: CrewAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter
    lateinit var rs: Film

    private lateinit var playerYoutube: YouTubePlayer.OnInitializedListener
    private val key = "AIzaSyAUkXpB5cX0hK6Hmc3Dl_aSZBCpSLlPACc"
    private val viewModel: FilmViewModel by viewModels()
    private var ytbFragment: YouTubePlayerSupportFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        val bundle: Bundle? = intent.extras
        val id = bundle!!.getInt("id")
        viewModel.loadData(id)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        ytbFragment =
            supportFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        onDataChange()
        setItemView()
        setEventListener()
        setContentView(binding.root)
    }

    private fun setItemView() {
        binding.apply {
            rcvCompanies.apply {
                adapter = companiesAdapter
                layoutManager =
                    LinearLayoutManager(this@FilmActivity, LinearLayoutManager.VERTICAL, false)
            }
            rcvCountries.apply {
                adapter = countriesAdapter
                layoutManager =
                    LinearLayoutManager(this@FilmActivity, LinearLayoutManager.HORIZONTAL, false)
            }
            rcvCrew.apply {
                adapter = crewAdapter
                layoutManager =
                    LinearLayoutManager(this@FilmActivity, LinearLayoutManager.VERTICAL, false)
            }
            rcvCast.apply {
                adapter = castAdapter
                layoutManager =
                    LinearLayoutManager(this@FilmActivity, LinearLayoutManager.VERTICAL, false)
            }
            rcvGenres.apply {
                adapter = genresAdapter
                layoutManager =
                    LinearLayoutManager(this@FilmActivity, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun onDataChange() {
        viewModel.apply {
            film.observe(this@FilmActivity)
            {
                rs = it
                binding.f = it
                binding.executePendingBindings()
                companiesAdapter.submitData(it.productionCompanies)
                countriesAdapter.submitData(it.productionCountries)
                genresAdapter.submitData(it.genres)
            }
            video.observe(this@FilmActivity)
            {
                playerYoutube = object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubePlayer?,
                        p2: Boolean
                    ) {
                        if (it.results[0].key != null)
                        {
                            p1?.loadVideo(it.results[0].key)
                            p1?.play()
                        }


                    }

                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {
                        Toast.makeText(applicationContext, "abc", Toast.LENGTH_SHORT).show()
                    }
                }
                //feature not error
                ytbFragment?.initialize(key, playerYoutube)
            }
            casts.observe(this@FilmActivity)
            {
                crewAdapter.submitData(it.crew)
                castAdapter.submitData(it.cast)
            }
        }
    }

    private fun setEventListener() {
        val intent = Intent(this@FilmActivity, PersonActivity::class.java)
        castAdapter.onClickItem = {
            intent.putExtra("person_id", it.id)
            startActivity(intent)
        }

        crewAdapter.onClickItem =
            {
                intent.putExtra("person_id", it.id)
                startActivity(intent)
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> true.also { finish() }
            R.id.add_wish_list -> true.also {
                viewModel.addToDb(rs)
                Toast.makeText(this,"Added to favorite",Toast.LENGTH_SHORT).show()
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)

    }
}