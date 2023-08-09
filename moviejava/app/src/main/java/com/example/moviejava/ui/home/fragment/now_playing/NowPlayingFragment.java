package com.example.moviejava.ui.home.fragment.now_playing;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.FragmentNowPlayingBinding;
import com.example.moviejava.ui.base.BaseFragment;
import com.example.moviejava.ui.film.FilmActivity;
import com.example.moviejava.ui.home.HomeViewModel;
import com.example.moviejava.ui.home.adapter.MovieAdapter;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class NowPlayingFragment extends BaseFragment<FragmentNowPlayingBinding> {

    private HomeViewModel viewModel;

    @Inject
    MovieAdapter viewAdapter;
    private int page = 1;

//    public NowPlayingFragment(@LayoutRes int contentLayoutId) {
//        super(contentLayoutId);
//    }

    @Override
    protected FragmentNowPlayingBinding onHandleViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentNowPlayingBinding.inflate(inflater, container, false);
    }

    @Override
    protected void onHandleObject(FragmentNowPlayingBinding rootView) {
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        dataOnChange();
        setViewAdapter();
    }

    @Override
    protected void onHandleEvents() {
        setEvent();
    }

    private void dataOnChange() {
        defaultObserve(viewModel.nowPlaying,null,()->{
                    binding().loading.setVisibility(View.GONE);
                    binding().rcvNowPlaying.setVisibility(View.VISIBLE);
                }, isLoading -> {
                    if (isLoading)
                        binding().loading.setVisibility(View.VISIBLE);
                },
                it->viewAdapter.submitData(it));
    }

    private void setViewAdapter() {
        binding().rcvNowPlaying.setAdapter(viewAdapter);
        binding().rcvNowPlaying.setLayoutManager(new GridLayoutManager(getContext(), 4));
        binding().rcvNowPlaying.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager!=null &&
                        layoutManager.findLastCompletelyVisibleItemPosition() == Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() - 1) {
                    page++;
                    viewModel.getUpComing(page);
                }
            }
        });
    }

    private void setEvent() {
        viewAdapter.onClick = results -> {
            Intent intent = new Intent(getActivity(), FilmActivity.class);
            intent.putExtra("film_id", results.id);
            startActivity(intent);
        };

        binding().rcvNowPlaying.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null &&
                        layoutManager.findLastCompletelyVisibleItemPosition() == Objects.requireNonNull(binding().rcvNowPlaying.getAdapter()).getItemCount() - 1) {
                    page++;
                    viewModel.getNowPlaying(page);
                }
            }
        });
    }


}