package com.example.androidproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.ItemFirebasemovieBinding
import com.example.androidproject.models.FirebaseMovie

class FirebaseMovieAdapter(
    var context: Context,
    var FirebaseMovieList: ArrayList<FirebaseMovie>
) : RecyclerView.Adapter<FirebaseMovieAdapter.FirebaseMovieViewHolder>() {
    inner class FirebaseMovieViewHolder(var item: ItemFirebasemovieBinding) :
        RecyclerView.ViewHolder(item.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemFirebasemovieBinding>(
            inflater,
            R.layout.item_firebasemovie,
            parent,
            false
        )
        return FirebaseMovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: FirebaseMovieViewHolder, position: Int) {
        holder.item.isMovie = FirebaseMovieList[position]
    }

    override fun getItemCount(): Int {
        return FirebaseMovieList.size
    }
}