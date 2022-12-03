package com.example.movie.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.data.local.entities.MovieEntity
import com.example.movie.databinding.ItemFavoriteBinding
import javax.inject.Inject


class FavoriteAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private var companies = mutableListOf<MovieEntity>()
    var onClickItem: ((MovieEntity) -> Unit)? = null
    var onDelete: ((MovieEntity) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<MovieEntity>) {
        companies.clear()
        companies.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(m: MovieEntity) {
            binding.m = m
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        companies[position].let {
            holder.onBind(it)
            holder.itemView.setOnClickListener { _ ->
                onClickItem?.invoke(it)
            }
            holder.binding.deleteFavorite.setOnClickListener { _ ->
                onDelete?.invoke(it)
            }
            //setScaleAnimation(holder.itemView)
        }
    }

    override fun getItemCount(): Int = companies.size
    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        anim.duration = 1000
        view.startAnimation(anim)
    }
}
