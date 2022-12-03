package com.example.moviejava.ui.film;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.ItemCountryBinding;
import com.example.moviejava.model.film.ProductionCountries;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    @Inject
    public CountriesAdapter() {

    }

    public void submitData(List<ProductionCountries> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    private List<ProductionCountries> countries = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemCountryBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCountryBinding binding;

        public ViewHolder(@NonNull ItemCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(ProductionCountries c) {
            binding.setC(c);
            binding.executePendingBindings();
        }
    }
}