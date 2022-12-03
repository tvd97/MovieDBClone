package com.example.moviejava.ui.film;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviejava.databinding.ItemCompaniesBinding;
import com.example.moviejava.model.film.ProductionCompanies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.ViewHolder> {
    @Inject
    public CompaniesAdapter() {

    }

    public void submitData(List<ProductionCompanies> companies) {
        this.companies = companies;
        notifyDataSetChanged();
    }

    private List<ProductionCompanies> companies = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemCompaniesBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(companies.get(position));
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCompaniesBinding binding;

        public ViewHolder(@NonNull ItemCompaniesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(ProductionCompanies c) {
            binding.setC(c);
            binding.executePendingBindings();
        }
    }
}
