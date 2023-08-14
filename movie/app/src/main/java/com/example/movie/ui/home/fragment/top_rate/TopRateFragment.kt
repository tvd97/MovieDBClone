package com.example.movie.ui.home.fragment.top_rate

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.movie.databinding.FragmentTopRateBinding
import com.example.movie.ui.base.BaseFragment
import com.example.movie.ui.film.FilmActivity
import com.example.movie.ui.home.HomeViewModel
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopRateFragment : BaseFragment<FragmentTopRateBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()
    private var page = 1

    @Inject
    lateinit var rateAdapter: MovieAdapter

    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentTopRateBinding = FragmentTopRateBinding.inflate(inflater, container, false)

    override fun setupObjects(rootView: ViewBinding?) {
        defaultObserver(viewModel.topRate, {
            binding.loading.visibility = View.GONE
            binding.rcvRate.visibility = View.VISIBLE
        }, { binding.loading.visibility = View.VISIBLE }, {
            it?.let { rateAdapter.submitData(it) }
        })
        binding.rcvRate.apply {
            adapter = rateAdapter
            layoutManager = GridLayoutManager(context, 4)
        }
    }

    override fun setupEvents() {
        rateAdapter.onClickItem = { r ->
            activity?.let {
                val intent = Intent(it, FilmActivity::class.java)
                intent.putExtra("id", r.id)
                it.startActivity(intent)
            }

        }
        binding.rcvRate.apply {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as GridLayoutManager
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter?.itemCount?.minus(
                            1
                        )
                    ) {
                        page++
                        viewModel.getTopRate(page)
                    }
                }
            })
        }
    }


}