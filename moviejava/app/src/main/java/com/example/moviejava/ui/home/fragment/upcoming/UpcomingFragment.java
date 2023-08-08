package com.example.moviejava.ui.home.fragment.upcoming;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.FragmentUpcomingBinding;
import com.example.moviejava.ui.base.BaseFragment;
import com.example.moviejava.ui.film.FilmActivity;
import com.example.moviejava.ui.home.HomeViewModel;
import com.example.moviejava.ui.home.adapter.MovieAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class UpcomingFragment extends BaseFragment<FragmentUpcomingBinding> {

    private HomeViewModel viewModel;

    @Inject
    MovieAdapter viewAdapter;
    private int page = 1;

    public UpcomingFragment(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }


    @Override
    protected FragmentUpcomingBinding onHandleViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentUpcomingBinding.inflate(inflater, container, false);
    }

    @Override
    protected void onHandleObject(FragmentUpcomingBinding rootView) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        binding().rcvUcm.setAdapter(viewAdapter);
        binding().rcvUcm.setLayoutManager(new GridLayoutManager(getContext(), 4));

        defaultObserve(viewModel.upcoming, null,
                () -> {
                    binding().loading.setVisibility(View.GONE);
                    binding().rcvUcm.setVisibility(View.VISIBLE);
                }, isLoading -> {
                    if (isLoading)
                        binding().loading.setVisibility(View.VISIBLE);
                },
                it -> viewAdapter.submitData(it));


    }

    @Override
    protected void onHandleEvents() {
        binding().rcvUcm.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        viewAdapter.onClick= results -> {
            Intent intent= new Intent(getActivity(), FilmActivity.class);
            intent.putExtra("film_id",results.id);
            startActivity(intent);
        };
    }

}