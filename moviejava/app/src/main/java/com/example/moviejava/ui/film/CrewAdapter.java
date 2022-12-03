package com.example.moviejava.ui.film;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.moviejava.databinding.ItemCrewBinding;
import com.example.moviejava.extentions.OnClickItem;
import com.example.moviejava.model.casts.Crew;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {
    public OnClickItem<Crew> onClick;
    @Inject
    public CrewAdapter() {
    }

    public void submitData(List<Crew> crews) {
        this.crews = crews;
        notifyDataSetChanged();
    }

    private List<Crew> crews = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemCrewBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(crews.get(position));
        holder.itemView.setOnClickListener(v-> onClick.onClick(crews.get(position)));
    }

    @Override
    public int getItemCount() {
        return crews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCrewBinding binding;

        public ViewHolder(@NonNull ItemCrewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Crew c) {
            binding.setC(c);
            binding.executePendingBindings();
        }
    }
}