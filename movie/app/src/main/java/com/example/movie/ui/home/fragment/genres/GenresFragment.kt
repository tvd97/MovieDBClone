package com.example.movie.ui.home.fragment.genres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.movie.databinding.FragmentGenresBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GenresFragment : BaseFragment<FragmentGenresBinding>() {
    private val viewModel: GenresViewModel by viewModels()


    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var filmAdapter: MovieAdapter
    private var genreId = 0
    private var page = 1

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentGenresBinding = FragmentGenresBinding.inflate(inflater, container, false)

    override fun setupObjects(rootView: ViewBinding?) {
        binding.rcvGenres.apply {
            adapter = genresAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rcvListFilm.apply {
            adapter = filmAdapter
            layoutManager = GridLayoutManager(context, 4)
        }
        viewModel.apply {
            defaultObserver(listGenres, null, null) {
                it?.let { genresAdapter.submitData(it) }
            }

            defaultObserver(results, {
                binding.loading.visibility = View.GONE
            }, {
                binding.loading.visibility = View.VISIBLE
            }) {
                it?.let {
                    filmAdapter.submitData(it)
                }
            }
            defaultObserver(isChose) {
                if (it) {
                    binding.apply {
                        loading.visibility = View.GONE
                        rcvListFilm.visibility = View.VISIBLE
                    }
                }

            }
        }
    }

    override fun setupEvents() {
        genresAdapter.onClickItem = {
            genreId = it.id!!
            page = 1
            viewModel.clearData()
            viewModel.getListFilmByGenres(id = it.id!!, page = 1)
        }
        binding.rcvListFilm.apply {
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
    }

}