package com.example.androidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidproject.fragments.FavoriteMoviesFragment
import com.example.androidproject.fragments.HomeFragment
import com.example.androidproject.fragments.JsonMoviesFragment
import com.example.androidproject.fragments.VideoPlaybackFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val videoPlaybackFragment = VideoPlaybackFragment()
        val jsonMoviesFragment = JsonMoviesFragment()
        val favoriteMoviesFragment = FavoriteMoviesFragment()

        makeCurrentFragment(homeFragment)

        val bottomNavigation =
            findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_videoPlayback -> makeCurrentFragment(videoPlaybackFragment)
                R.id.ic_allMovies -> makeCurrentFragment(jsonMoviesFragment)
                R.id.ic_favoriteMovies -> makeCurrentFragment(favoriteMoviesFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}