package com.example.moviejava.ui.home.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.ItemFilmBackdropBinding;
import com.example.moviejava.extentions.OnClickItem;
import com.example.moviejava.extentions.ViewDiffUtil;
import com.example.moviejava.model.Results;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    public OnClickItem<Results> onClick;
    private final List<Results> list= new ArrayList<>();

    @Inject
    public MovieAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitData(List<Results> data) {
        ViewDiffUtil viewUtil= new ViewDiffUtil(list,data);
        DiffUtil.DiffResult  diffResult= DiffUtil.calculateDiff(viewUtil);
        list.clear();
        list.addAll(data);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemFilmBackdropBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
        holder.itemView.setOnClickListener(v ->
                onClick.onClick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFilmBackdropBinding binding;

        public ViewHolder(@NonNull ItemFilmBackdropBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void onBind(Results r) {
            binding.setRs(r);
            binding.executePendingBindings();
        }

    }
}
