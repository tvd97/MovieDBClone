package com.example.moviejava.ui.favorite;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.data.local.entities.MovieEntity;
import com.example.moviejava.databinding.ItemFavoriteBinding;
import com.example.moviejava.extentions.OnClickItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<MovieEntity> list= new ArrayList<>();
    public OnClickItem<MovieEntity> onClick,onDelete;

    @Inject
    public FavoriteAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemFavoriteBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
        holder.itemView.setOnClickListener(
                view -> onClick.onClick(list.get(position))
        );
        holder.binding.deleteFavorite.setOnClickListener( view -> onDelete.onClick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFavoriteBinding binding;

        public ViewHolder(@NonNull ItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(MovieEntity m) {
            binding.setM(m);
            binding.executePendingBindings();
        }
    }

    public void submitData(List<MovieEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
