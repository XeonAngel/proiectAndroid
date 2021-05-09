package com.example.androidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidproject.FavoriteMoviesApplication
import com.example.androidproject.R
import com.example.androidproject.models.FavoriteMovie
import com.example.androidproject.viewModel.FavoriteMovieViewModel
import com.example.androidproject.viewModel.FavoriteMoviesViewModelFactory

private const val MovieTitle = "movieTitle"
private const val MovieImbdId = "movieIMBD"
private const val MovieYear = "movieYear"

class AddFavoriteMovieFragment : Fragment() {
    private var movieTitle: String? = null
    private var movieImbdId: String? = null
    private var movieYear: String? = null

    private lateinit var titleView: TextView
    private lateinit var imbdIdView: TextView
    private lateinit var yearView: TextView
    private lateinit var scoreEditText: EditText
    private lateinit var saveButton: Button

    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel
    private lateinit var factory: FavoriteMoviesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieTitle = it.getString(MovieTitle)
            movieImbdId = it.getString(MovieImbdId)
            movieYear = it.getString(MovieYear)
        }

        factory = FavoriteMoviesViewModelFactory(
            (activity!!.application as FavoriteMoviesApplication).repository
        )
        favoriteMovieViewModel = factory.create(FavoriteMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_favorite_movie, container, false)

        titleView = rootView.findViewById(R.id.addMovieName)
        imbdIdView = rootView.findViewById(R.id.addMovieImbdId)
        yearView = rootView.findViewById(R.id.addMovieYear)
        scoreEditText = rootView.findViewById(R.id.addMovieScore)
        saveButton = rootView.findViewById(R.id.addFavoriteMovie)

        titleView.text = movieTitle
        imbdIdView.text = movieImbdId
        yearView.text = movieYear

        saveButton.setOnClickListener {
            if (scoreEditText.text.isEmpty()) {
                Toast.makeText(
                    activity!!.applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val newMovie = FavoriteMovie(
                    titleView.text.toString(),
                    yearView.text.toString(),
                    imbdIdView.text.toString(),
                    scoreEditText.text.toString().toInt()
                )
                favoriteMovieViewModel.insertOrUpdate(activity!! as AppCompatActivity, newMovie)
            }
        }
        return rootView
    }
}