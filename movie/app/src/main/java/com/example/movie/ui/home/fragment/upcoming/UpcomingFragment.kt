package com.example.movie.ui.home.fragment.upcoming

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.FragmentUpcomingBinding
import com.example.movie.ui.film.FilmActivity
import com.example.movie.ui.home.HomeViewModel
import com.example.movie.ui.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding
    private val viewModel: HomeViewModel by activityViewModels ()

    @Inject
    lateinit var viewAdapter: MovieAdapter
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        binding.rcvUcm.apply {
            adapter = viewAdapter
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
                        viewModel.getUpComing(page)
                    }
                }
            })
        }
//        viewModel.apply {
//            listGenres.observe(viewLifecycleOwner)
//            {
//
//                viewAdapter.submitData(it)
//            }
//            isLoading.observe(viewLifecycleOwner)
//            {
//                if (it==true) {
//                    binding.loading.visibility = View.VISIBLE
//                } else {
//                    binding.loading.visibility = View.GONE
//                    binding.rcvUcm.visibility = View.VISIBLE
//                }
//            }
//        }

        viewAdapter.onClickItem = { r ->
            activity?.let {
                val intent = Intent(it, FilmActivity::class.java)
                intent.putExtra("id", r.id)
                it.startActivity(intent)
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            upcoming
                .observe(viewLifecycleOwner)
            {

                viewAdapter.submitData(it)
            }
            isLoading.observe(viewLifecycleOwner)
            {
                if (it==true) {
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.loading.visibility = View.GONE
                    binding.rcvUcm.visibility = View.VISIBLE
                }
            }
        }
    }



}