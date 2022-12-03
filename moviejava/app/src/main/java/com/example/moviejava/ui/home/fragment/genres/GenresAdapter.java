package com.example.moviejava.ui.home.fragment.genres;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.ItemGenresBinding;
import com.example.moviejava.extentions.OnClickItem;
import com.example.moviejava.model.Genres;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;




public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    @Inject
    public GenresAdapter() {
    }

    private final List<Genres> listGenres= new ArrayList<>();
    public OnClickItem<Genres> onClickItem;

    @SuppressLint("NotifyDataSetChanged")
    public void submitData(List<Genres> data) {
        listGenres.clear();
        listGenres.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemGenresBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(listGenres.get(position));
        holder.itemView.setOnClickListener(view -> onClickItem.onClick(listGenres.get(position)));
    }

    @Override
    public int getItemCount() {
        return listGenres.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemGenresBinding binding;

        public ViewHolder(@NonNull ItemGenresBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Genres g) {
            binding.setG(g);
            binding.executePendingBindings();
        }
    }
}