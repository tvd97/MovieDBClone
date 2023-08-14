package com.example.movie.ui.home.fragment.popular

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.movie.databinding.FragmentPopularBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.film.FilmActivity
import com.example.movie.ui.home.HomeViewModel
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>() {
    private val viewModel: HomeViewModel by activityViewModels()


    @Inject
    lateinit var popularAdapter: MovieAdapter
    private var page = 1

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentPopularBinding = FragmentPopularBinding.inflate(inflater, container, false)

    override fun setupObjects(rootView: ViewBinding?) {
        binding.rcvPopular.apply {
            adapter = popularAdapter
            layoutManager = GridLayoutManager(context, 4)
        }

        defaultObserver(viewModel.popular, {
            binding.loading.visibility = View.GONE
            binding.rcvPopular.visibility = View.VISIBLE
        }, { binding.loading.visibility = View.VISIBLE }, {
            it?.let { popularAdapter.submitData(it) }
        })

    }

    override fun setupEvents() {
        popularAdapter.onClickItem = { r ->
            activity?.let {
                val intent = Intent(it, FilmActivity::class.java)
                intent.putExtra("id", r.id)
                it.startActivity(intent)
            }
        }
        binding.rcvPopular.apply {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter?.itemCount?.minus(
                            1
                        )
                    ) {
                        page++
                        viewModel.getPopular(page)
                    }
                }
            })
        }
    }

}