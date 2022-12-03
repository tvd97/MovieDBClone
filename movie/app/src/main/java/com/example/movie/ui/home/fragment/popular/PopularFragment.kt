package com.example.movie.ui.home.fragment.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.FragmentPopularBinding
import com.example.movie.ui.film.FilmActivity
import com.example.movie.ui.home.HomeViewModel
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : Fragment() {
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FragmentPopularBinding

    @Inject
    lateinit var popularAdapter: MovieAdapter
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        viewModel.apply {
            popular.observe(viewLifecycleOwner)
            {
                popularAdapter.submitData(it)
            }
            isLoading.observe(viewLifecycleOwner)
            {
                if (it == true) {
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.loading.visibility = View.GONE
                    binding.rcvPopular.visibility = View.VISIBLE
                }
            }
        }
        binding.rcvPopular.apply {
            adapter = popularAdapter
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
                        viewModel.getPopular(page)
                    }
                }
            })
        }
        popularAdapter.onClickItem = { r ->
            activity?.let {
                val intent = Intent(it, FilmActivity::class.java)
                intent.putExtra("id", r.id)
                it.startActivity(intent)
            }

        }
        return binding.root
    }

}