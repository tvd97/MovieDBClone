package com.example.movie.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemFilmBackdropBinding
import com.example.movie.extensions.ViewDiffUtil
import com.example.movie.model.Results
import javax.inject.Inject

class MovieAdapter @Inject constructor() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var onClickItem: ((Results) -> Unit)? = null
    private var listGenres = mutableListOf<Results>()

    fun submitData(data: List<Results>) {
        val diffUtil = ViewDiffUtil(listGenres, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listGenres.clear()
        listGenres.addAll(data)
        diffResult.dispatchUpdatesTo(this)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFilmBackdropBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listGenres[position].let {
            holder.onBind(it)
            holder.itemView.setOnClickListener { _ ->
                onClickItem?.invoke(it)
            }

        }
    }

    override fun getItemCount() = listGenres.size


    inner class ViewHolder(private val binding: ItemFilmBackdropBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(rs: Results) {
            binding.rs = rs
            binding.executePendingBindings()
        }
    }
}