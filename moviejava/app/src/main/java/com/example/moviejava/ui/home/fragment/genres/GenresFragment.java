package com.example.moviejava.ui.home.fragment.genres;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.FragmentGenresBinding;
import com.example.moviejava.ui.film.FilmActivity;
import com.example.moviejava.ui.home.adapter.MovieAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class GenresFragment extends Fragment {
    private GenresViewModel viewModel;
    private FragmentGenresBinding binding;

    @Inject
    GenresAdapter genresAdapter;

    @Inject
    MovieAdapter filmAdapter;
    private int genreId = 0;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGenresBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GenresViewModel.class);
        dataOnChange();
        setViewItem();
        setEventAdapter();

        return binding.getRoot();
    }

    private void dataOnChange() {
        viewModel.getGenres().observe(getViewLifecycleOwner(), list -> genresAdapter.submitData(list));
        viewModel.getResults().observe(getViewLifecycleOwner(), list -> filmAdapter.submitData(list));
        viewModel.getChose().observe(getViewLifecycleOwner(), it -> {
            if (it) {
                binding.loading.setVisibility(View.GONE);
                binding.rcvListFilm.setVisibility(View.VISIBLE);
            }

        });

    }

    private void setViewItem() {
        binding.rcvGenres.setAdapter(genresAdapter);
        binding.rcvGenres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        binding.rcvListFilm.setAdapter(filmAdapter);
        binding.rcvListFilm.setLayoutManager(new GridLayoutManager(getContext(), 4));
        //load more item in last item recyclerview
        binding.rcvListFilm.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager!=null&&layoutManager.findLastCompletelyVisibleItemPosition() == binding.rcvListFilm.getAdapter().getItemCount() - 1) {
                    page++;
                    viewModel.setResults(genreId, page);
                }
            }
        });
    }

    private void setEventAdapter() {
        //item recyclerview genres click item
        genresAdapter.onClickItem = genres -> {
            genreId = genres.id;
            page = 1;
            viewModel.clearData();
            viewModel.setResults(genres.id, page = 1);
        };

        //item recyclerview film click item
        filmAdapter.onClick = results -> {
            Intent intent = new Intent(requireActivity(), FilmActivity.class);
            intent.putExtra("film_id", results.id);
            startActivity(intent);
        };
    }

}