package com.example.androidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidproject.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val videoPlaybackFragment = VideoPlaybackFragment()
        val jsonMoviesFragment = JsonMoviesFragment()
        val captureImageFragment = CaptureImageFragment()
        val favoriteMoviesFragment = FavoriteMoviesFragment()

        makeCurrentFragment(homeFragment)

        val bottomNavigation =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_videoPlayback -> makeCurrentFragment(videoPlaybackFragment)
                R.id.ic_allMovies -> makeCurrentFragment(jsonMoviesFragment)
                R.id.ic_captureImage -> makeCurrentFragment(captureImageFragment)
                R.id.ic_favoriteMovies -> makeCurrentFragment(favoriteMoviesFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_content, fragment)
            commit()
        }
}

//val sendIntent: Intent = Intent().apply {
//    action = Intent.ACTION_SEND
//    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
//    type = "text/plain"
//}
//
//val shareIntent = Intent.createChooser(sendIntent, null)
//startActivity(shareIntent)