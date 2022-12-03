package com.example.movie.ui.home.fragment.genres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.FragmentGenresBinding
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GenresFragment : Fragment() {
    private val viewModel: GenresViewModel by viewModels()
    private lateinit var binding: FragmentGenresBinding

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var filmAdapter: MovieAdapter
    private var genreId = 0
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGenresBinding.inflate(inflater, container, false)
        viewModel.apply {
            listGenres.observe(viewLifecycleOwner)
            {
                genresAdapter.submitData(it)
            }
            results.observe(viewLifecycleOwner)
            {
                filmAdapter.submitData(it)

            }
            isChose.observe(viewLifecycleOwner)
            {
                if (it == true) {
                    binding.apply {
                        loading.visibility = View.GONE
                        rcvListFilm.visibility = View.VISIBLE
                    }
                }

            }
        }
        genresAdapter.onClickItem = {
            genreId = it.id!!
            page = 1
            viewModel.clearData()
            viewModel.getListFilmByGenres(id = it.id!!, page = 1)
        }
        binding.rcvGenres.apply {
            adapter = genresAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rcvListFilm.apply {
            adapter = filmAdapter
            layoutManager = GridLayoutManager(context, 4)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter?.itemCount?.minus(
                            1
                        )
                    ) {
                        page++
                        viewModel.getListFilmByGenres(id = genreId, page = page)
                    }
                }
            })
        }
        return binding.root
    }


}