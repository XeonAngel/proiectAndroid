package com.example.androidproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.ItemJsonmovieBinding
import com.example.androidproject.models.JsonMovie

class jsonMovieAdapter(
    var context: Context,
    var jsonMovieList: ArrayList<JsonMovie>
) : RecyclerView.Adapter<jsonMovieAdapter.JsonMovieViewHolder>() {

    inner class JsonMovieViewHolder(var item: ItemJsonmovieBinding) :
        RecyclerView.ViewHolder(item.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemJsonmovieBinding>(
            inflater,
            R.layout.item_jsonmovie,
            parent,
            false
        )
        return JsonMovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: JsonMovieViewHolder, position: Int) {
        holder.item.isMovie = jsonMovieList[position]
    }

    override fun getItemCount(): Int {
        return  jsonMovieList.size
    }
}