package com.example.androidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.FavoriteMoviesApplication
import com.example.androidproject.R
import com.example.androidproject.adapters.FavoriteMovieAdapter
import com.example.androidproject.viewModel.FavoriteMovieViewModel
import com.example.androidproject.viewModel.FavoriteMoviesViewModelFactory

class FavoriteMoviesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteMovieAdapter

    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel
    private lateinit var factory: FavoriteMoviesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        factory = FavoriteMoviesViewModelFactory(
            (activity!!.application as FavoriteMoviesApplication).repository
        )
        favoriteMovieViewModel = factory.create(FavoriteMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_movies, container, false)

        recyclerView = rootView.findViewById(R.id.favoriteMoviesRecyclerview)
        adapter = FavoriteMovieAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext().applicationContext)

        favoriteMovieViewModel.allMoviesByName.observe(this, { movies ->
            movies.let { adapter.submitList(it) }
        })

        return rootView
    }
}