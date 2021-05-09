package com.example.androidproject.viewModel

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.androidproject.R
import com.example.androidproject.fragments.FavoriteMoviesFragment
import com.example.androidproject.models.FavoriteMovie
import com.example.androidproject.repositories.FavoriteMoviesRepository
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val repository: FavoriteMoviesRepository) : ViewModel() {
    val allMoviesByName: LiveData<List<FavoriteMovie>> = repository.allMoviesByName.asLiveData()

    val allMoviesByScore: LiveData<List<FavoriteMovie>> = repository.allMoviesByScore.asLiveData()

    fun insertOrUpdate(activity: AppCompatActivity, newMovie: FavoriteMovie) =
        viewModelScope.launch {
            if (repository.getMovieByID(newMovie.imdbID) == null) {
                repository.insert(newMovie)
                Toast.makeText(activity.applicationContext, R.string.inserted, Toast.LENGTH_LONG)
                    .show()
            } else {
                repository.update(newMovie)
                Toast.makeText(activity.applicationContext, R.string.updated, Toast.LENGTH_LONG)
                    .show()
            }
            val favoriteMoviesFragment = FavoriteMoviesFragment()
            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_content, favoriteMoviesFragment)
                commit()
            }
        }

    fun insert(favoriteMovie: FavoriteMovie) = viewModelScope.launch {
        repository.insert(favoriteMovie)
    }

    fun update(favoriteMovie: FavoriteMovie) = viewModelScope.launch {
        repository.update(favoriteMovie)
    }
}

class FavoriteMoviesViewModelFactory(private val repository: FavoriteMoviesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteMovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}