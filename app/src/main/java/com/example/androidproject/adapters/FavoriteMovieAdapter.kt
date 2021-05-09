package com.example.androidproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.models.FavoriteMovie

class FavoriteMovieAdapter : ListAdapter<FavoriteMovie, FavoriteMovieAdapter.FavoriteMovieViewHolder>(FavoriteMovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val favoriteMovieTitleItemView: TextView = itemView.findViewById(R.id.favoriteMovieTitle)
        private val favoriteMovieImbdIdItemView: TextView = itemView.findViewById(R.id.favoriteMovieImbdId)
        private val favoriteMovieScoreItemView: TextView = itemView.findViewById(R.id.favoriteMovieScore)

        fun bind(item: FavoriteMovie) {
            favoriteMovieTitleItemView.text = item.title
            favoriteMovieImbdIdItemView.text = item.imdbID
            favoriteMovieScoreItemView.text = "My score " + item.score.toString()
        }

        companion object {
            fun create(parent: ViewGroup): FavoriteMovieViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_favorite_movie, parent, false)
                return FavoriteMovieViewHolder(view)
            }
        }
    }

    class FavoriteMovieComparator : DiffUtil.ItemCallback<FavoriteMovie>() {
        override fun areItemsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteMovie, newItem: FavoriteMovie): Boolean {
            return oldItem.title == newItem.title
        }
    }
}