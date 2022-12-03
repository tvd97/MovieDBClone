package com.example.movie.ui.home.fragment.top_rate

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.FragmentTopRateBinding
import com.example.movie.ui.film.FilmActivity
import com.example.movie.ui.home.HomeViewModel
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopRateFragment : Fragment() {

    private lateinit var binding: FragmentTopRateBinding
    private val viewModel: HomeViewModel by activityViewModels ()
    private var page = 1

    @Inject
    lateinit var rateAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRateBinding.inflate(inflater, container, false)
        viewModel.apply {
            topRate.observe(viewLifecycleOwner)
            {
                rateAdapter.submitData(it)
            }
            isLoading.observe(viewLifecycleOwner)
            {
                if (it == true) {
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.loading.visibility = View.GONE
                    binding.rcvRate.visibility = View.VISIBLE
                }
            }
        }
        binding.rcvRate.apply {
            adapter = rateAdapter
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
                        viewModel.getTopRate(page)
                    }
                }
            })
        }
        rateAdapter.onClickItem = { r ->
            activity?.let {
                val intent = Intent(it, FilmActivity::class.java)
                intent.putExtra("id", r.id)
                it.startActivity(intent)
            }

        }

        // Inflate the layout for this fragment
        return binding.root
    }


}