package com.example.androidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.adapters.FirebaseMovieAdapter
import com.example.androidproject.models.FirebaseMovie
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirebaseMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirebaseMoviesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieList: ArrayList<FirebaseMovie>
    private lateinit var adapter: FirebaseMovieAdapter

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
        val rootView = inflater.inflate(R.layout.fragment_firebase_movies, container, false)

        movieList = ArrayList()
        adapter = FirebaseMovieAdapter(requireContext().applicationContext, movieList)

        recyclerView = rootView.findViewById(R.id.firebaseMoviesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        getMovies()

        return rootView
    }

    private fun getMovies() {
        database = FirebaseDatabase.getInstance().getReference("movies")
        database.get()
            .addOnSuccessListener { movie ->
                if (movie != null)
                {
                    for (movieSnapshot in movie.children) {
                        val movieVal = movieSnapshot.getValue(FirebaseMovie::class.java)
                        movieList.add(movieVal!!)
                    }
                    recyclerView.adapter = adapter
                }
            }
        /*database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (movieSnapshot in snapshot.children) {
                        val movie = movieSnapshot.getValue(FirebaseMovie::class.java)
                        movieList.add(movie!!)
                    }
                    recyclerView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext().applicationContext,
                    error.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirebaseMoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirebaseMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}