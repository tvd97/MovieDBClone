package com.example.movie.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.databinding.ActivityFavoriteBinding
import com.example.movie.ui.base.BaseActivity
import com.example.movie.ui.film.FilmActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter
    private val viewModel: FavoriteViewModel by viewModels()

    override fun getLayoutBinding(): ActivityFavoriteBinding {
        return ActivityFavoriteBinding.inflate(layoutInflater)
    }

    override fun onHandleObject() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        defaultObserver(viewModel.movies){
            favoriteAdapter.submitData(it)
        }

        binding.rcvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager =
                LinearLayoutManager(this@FavoriteActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onHandleEvent() {
        favoriteAdapter.onClickItem = {
            val intent = Intent(this, FilmActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }
        favoriteAdapter.onDelete = {
            viewModel.deleteRecord(it)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> true.also { finish() }
        }
        return super.onOptionsItemSelected(item)

    }

//    private fun setSwipe() {
//        val simpleItemTouchCallback = object :
//            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                if (direction == ItemTouchHelper.LEFT) {
//                    favoriteAdapter.notifyItemChanged(viewHolder.adapterPosition)
//                    findViewById<View>(R.id.delete_favorite).visibility=View.VISIBLE
//                }
//
//            }
//
//
//        }
//        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
//        itemTouchHelper.attachToRecyclerView(binding.rcvFavorite)
//    }
}