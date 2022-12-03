package com.example.moviejava.ui.home.fragment.top_rate;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.FragmentTopRateBinding;
import com.example.moviejava.ui.film.FilmActivity;
import com.example.moviejava.ui.home.HomeViewModel;
import com.example.moviejava.ui.home.adapter.MovieAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class TopRateFragment extends Fragment {

    private FragmentTopRateBinding binding;
    private HomeViewModel viewModel;

    @Inject
    MovieAdapter viewAdapter;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTopRateBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding.rcvRate.setAdapter(viewAdapter);
        binding.rcvRate.setLayoutManager(new GridLayoutManager(getContext(), 4));
        binding.rcvRate.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1) {
                    page++;
                    viewModel.getUpComing(page);
                }
            }
        });
        viewModel.topRate.observe(getViewLifecycleOwner(), it -> viewAdapter.submitData(it));
        viewModel.isLoading.observe(getViewLifecycleOwner(), it -> {
            if (it) {
                binding.loading.setVisibility(View.VISIBLE);
            } else {
                binding.loading.setVisibility(View.GONE);
                binding.rcvRate.setVisibility(View.VISIBLE);
            }
        });
        viewAdapter.onClick = results -> {
            Intent intent = new Intent(getActivity(), FilmActivity.class);
            intent.putExtra("film_id", results.id);
            startActivity(intent);
        };
        return binding.getRoot();
    }



}