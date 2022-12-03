package com.example.moviejava.ui.film;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.ItemCastBinding;
import com.example.moviejava.extentions.OnClickItem;
import com.example.moviejava.model.casts.Cast;
import com.example.moviejava.model.casts.Crew;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    public OnClickItem<Cast> onClick;
    @Inject
    public CastAdapter() {

    }

    public void submitData(List<Cast> companies) {
        this.casts = companies;
        notifyDataSetChanged();
    }

    private List<Cast> casts = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemCastBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(casts.get(position));
        holder.itemView.setOnClickListener(v-> onClick.onClick(casts.get(position)));
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCastBinding binding;

        public ViewHolder(@NonNull ItemCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Cast c) {
            binding.setC(c);
            binding.executePendingBindings();
        }
    }
}