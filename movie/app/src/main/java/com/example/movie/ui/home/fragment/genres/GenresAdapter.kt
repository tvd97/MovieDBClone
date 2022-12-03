package com.example.movie.ui.home.fragment.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemGenresBinding
import com.example.movie.model.Genres
import javax.inject.Inject


class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private var listGenres = mutableListOf<Genres>()
    var onClickItem: ((Genres) -> Unit)? = null
    fun submitData(data: List<Genres>) {
        listGenres.clear()
        listGenres.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGenresBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(genres: Genres) {
            binding.g = genres
            binding.executePendingBindings()
        }
    }
}