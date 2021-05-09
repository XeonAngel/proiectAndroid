package com.example.androidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.adapters.JsonMovieAdapter
import com.example.androidproject.api.ApiInterface
import com.example.androidproject.models.JsonMovie
import com.example.androidproject.models.JsonResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonMoviesFragment : Fragment() {
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieList: ArrayList<JsonMovie>
    private lateinit var adapter: JsonMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_json_movies, container, false)

        movieList = ArrayList()
        adapter = JsonMovieAdapter(requireContext().applicationContext, movieList)

        searchView = rootView.findViewById(R.id.jsonMovieSearch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                movieList.clear()
                getMovies(query)
                return true
            }
        })

        recyclerView = rootView.findViewById(R.id.jsonMoviesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        //recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        return rootView
    }

    private fun getMovies(searchString: String) {
        val apiInterface = ApiInterface.create().getMovies("ca513130", searchString, "1")

        apiInterface.enqueue(object : Callback<JsonResult> {
            override fun onResponse(call: Call<JsonResult>?, response: Response<JsonResult>?) {
                if (response?.body() != null) {
                    if (response.body()!!.Response == "False") {
                        Toast.makeText(
                            requireContext().applicationContext,
                            "No movie found",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else {
                    if (response.body()!!.totalResults.toInt() >= 1) {
                        for (movie in response.body()!!.Search) {
                            movieList.add(movie)
                        }
                    }}
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<JsonResult>?, t: Throwable?) {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Failed to load movies",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}