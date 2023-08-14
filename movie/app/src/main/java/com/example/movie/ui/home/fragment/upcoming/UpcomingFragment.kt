package com.example.movie.ui.home.fragment.upcoming

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.movie.databinding.FragmentUpcomingBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.film.FilmActivity
import com.example.movie.ui.home.HomeViewModel
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpcomingFragment : BaseFragment<FragmentUpcomingBinding>() {


    private val viewModel: HomeViewModel by activityViewModels()

    @Inject
    lateinit var viewAdapter: MovieAdapter
    private var page = 1

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentUpcomingBinding = FragmentUpcomingBinding.inflate(inflater, container, false)

    override fun setupObjects(rootView: ViewBinding?) {
        defaultObserver(viewModel.upcoming, {
            binding.loading.visibility = View.GONE
            binding.rcvUcm.visibility = View.VISIBLE
        }, { binding.loading.visibility = View.VISIBLE }, {
            it?.let { viewAdapter.submitData(it) }
        })
        binding.rcvUcm.apply {
            adapter = viewAdapter
            layoutManager = GridLayoutManager(context, 4)
        }
    }

    override fun setupEvents() {
        viewAdapter.onClickItem = { r ->
            activity?.let {
                val intent = Intent(it, FilmActivity::class.java)
                intent.putExtra("id", r.id)
                it.startActivity(intent)
            }

        }
        binding.rcvUcm.apply {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter?.itemCount?.minus(
                            1
                        )
                    ) {
                        page++
                        viewModel.getUpComing(page)
                    }
                }
            })
        }
    }


}