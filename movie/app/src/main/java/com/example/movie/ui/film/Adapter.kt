package com.example.movie.ui.film

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ItemCastBinding
import com.example.movie.databinding.ItemCompaniesBinding
import com.example.movie.databinding.ItemCountryBinding
import com.example.movie.databinding.ItemCrewBinding
import com.example.movie.model.Results
import com.example.movie.model.casts.Cast
import com.example.movie.model.casts.Crew
import com.example.movie.model.film.ProductionCompanies
import com.example.movie.model.film.ProductionCountries
import javax.inject.Inject

class CompaniesAdapter @Inject constructor() : RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {
    private var companies = mutableListOf<ProductionCompanies>()
    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<ProductionCompanies>) {
        companies.clear()
        companies.addAll(data)
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val binding: ItemCompaniesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(c: ProductionCompanies) {
            c.logoPath="https://image.tmdb.org/t/p/w300${c.logoPath}"
            binding.c = c
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCompaniesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        companies[position].let {
            holder.onBind(it)
        }
    }

    override fun getItemCount(): Int =companies.size
}

class CountriesAdapter @Inject constructor()  : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(c: ProductionCountries) {
            binding.c = c
            binding.executePendingBindings()
        }
    }

    private var countries = mutableListOf<ProductionCountries>()
    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<ProductionCountries>) {
        countries.clear()
        countries.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        countries[position].let {
            holder.onBind(it)
        }
    }

    override fun getItemCount(): Int = countries.size


}
class CastAdapter @Inject constructor()  : RecyclerView.Adapter<CastAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(c: Cast) {
            binding.c = c
            binding.executePendingBindings()
        }
    }
    var onClickItem: ((Cast) -> Unit)? = null
    private var countries = mutableListOf<Cast>()
    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<Cast>) {
        countries.clear()
        countries.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        countries[position].let {
            holder.onBind(it)
            holder.itemView.setOnClickListener { _ ->
                onClickItem?.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int = countries.size


}
class CrewAdapter @Inject constructor()  : RecyclerView.Adapter<CrewAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(c: Crew) {
            binding.c = c
            binding.executePendingBindings()
        }
    }
    var onClickItem: ((Crew) -> Unit)? = null
    private var countries = mutableListOf<Crew>()
    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data: List<Crew>) {
        countries.clear()
        countries.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCrewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        countries[position].let {
            holder.onBind(it)
            holder.itemView.setOnClickListener { _ ->
                onClickItem?.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int = countries.size


}
