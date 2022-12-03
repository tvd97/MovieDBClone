package com.example.movie.extensions

import androidx.recyclerview.widget.DiffUtil
import com.example.movie.model.Results

class ViewDiffUtil(
    private val oldList: List<Results>,
    private val newList: List<Results>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]

    }
}