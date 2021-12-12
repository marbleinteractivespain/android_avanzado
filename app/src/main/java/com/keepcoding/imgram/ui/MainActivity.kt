package com.keepcoding.imgram.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.google.android.material.navigation.NavigationBarView
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ActivityMainBinding
import com.keepcoding.imgram.ui.movies.MovieFragment
import com.keepcoding.imgram.ui.tvshows.TvShowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, TvShowFragment::class.java, bundleOf(Pair("key", "value")))
            .commit()


        with(binding){
            bottomNavigationView.setOnItemSelectedListener(object: NavigationBarView.OnItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {

                    when(item.itemId){
                        R.id.menu_tv_shows -> {
                            supportFragmentManager.beginTransaction()
                                .replace(binding.fragmentContainer.id, TvShowFragment::class.java, bundleOf())
                                .commit()
                        }
                        R.id.menu_movies -> {
                            supportFragmentManager.beginTransaction()
                                .replace(binding.fragmentContainer.id, MovieFragment::class.java, bundleOf())
                                .commit()
                        }
                        else -> {
                            Log.d("ActivityMain", "No deberías estar aqui")
                        }
                    }

                    return true
                }

            })

        }

    }

}