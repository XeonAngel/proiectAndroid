package com.example.androidproject.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.ItemJsonMovieBinding
import com.example.androidproject.fragments.AddFavoriteMovieFragment
import com.example.androidproject.models.JsonMovie

class JsonMovieAdapter(
    var context: Context,
    var jsonMovieList: ArrayList<JsonMovie>
) : RecyclerView.Adapter<JsonMovieAdapter.JsonMovieViewHolder>() {

    inner class JsonMovieViewHolder(var item: ItemJsonMovieBinding) :
        RecyclerView.ViewHolder(item.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemJsonMovieBinding>(
            inflater,
            R.layout.item_json_movie,
            parent,
            false
        )
        return JsonMovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: JsonMovieViewHolder, position: Int) {
        holder.item.isMovie = jsonMovieList[position]
        holder.item.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("movieTitle", jsonMovieList[position].title)
            bundle.putString("movieIMBD", jsonMovieList[position].imdbID)
            bundle.putString("movieYear", jsonMovieList[position].year)
            val addMovFragment = AddFavoriteMovieFragment()
            addMovFragment.arguments = bundle

            val activity = it.context as? AppCompatActivity
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fl_content, addMovFragment)
                commit()
            }
        }
    }

    override fun getItemCount(): Int {
        return jsonMovieList.size
    }
}