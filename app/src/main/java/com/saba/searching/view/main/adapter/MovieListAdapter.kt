package com.saba.searching.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saba.searching.R
import com.saba.searching.view.main.model.MovieDetailsModel
import kotlinx.android.synthetic.main.row_item_movie_list.view.*

class MovieListAdapter(
    private val onClick: (MovieDetailsModel) -> Unit
) : ListAdapter<MovieDetailsModel, MovieListAdapter.MovieListViewHolder>(
    object : DiffUtil.ItemCallback<MovieDetailsModel>() {
        override fun areItemsTheSame(
            oldItem: MovieDetailsModel,
            newItem: MovieDetailsModel
        ): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(
            oldItem: MovieDetailsModel,
            newItem: MovieDetailsModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_item_movie_list,
            parent,
            false
        )
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(item: MovieDetailsModel) {

            Glide.with(itemView.context)
                .load(item.cover ?: "")
                .placeholder(R.drawable.no_poster)
                .into(itemView.img_item_movie_cover)

            itemView.txt_movie_name.text = item.movieTitle
            itemView.txt_movie_director.text = item.director
            itemView.txt_movie_desc.text = item.descr
            itemView.txt_movie_rate.text = item.imdbRate
            itemView.txt_publish_year.text = "سال انتشار : " + item.proYear

            itemView.setOnClickListener {
                onClick.invoke(item)
            }


        }
    }
}
