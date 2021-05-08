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
import com.example.androidproject.adapters.jsonMovieAdapter
import com.example.androidproject.api.ApiInterface
import com.example.androidproject.models.JsonMovie
import com.example.androidproject.models.JsonResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JsonMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JsonMoviesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieList: ArrayList<JsonMovie>
    private lateinit var adapter: jsonMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_json_movies, container, false)

        movieList = ArrayList()
        adapter = jsonMovieAdapter(requireContext().applicationContext, movieList)

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
        recyclerView.setHasFixedSize(true)
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
                    "Fail",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment jsonMovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JsonMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}