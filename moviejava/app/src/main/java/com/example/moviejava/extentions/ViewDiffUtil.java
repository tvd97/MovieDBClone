package com.example.moviejava.extentions;

import androidx.recyclerview.widget.DiffUtil;

import com.example.moviejava.model.Results;

import java.util.List;


public class ViewDiffUtil extends DiffUtil.Callback {

    private List<Results> oldList;
    private List<Results> newList;

    public ViewDiffUtil(List<Results> oldList, List<Results> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition)==newList.get(newItemPosition);
    }
}