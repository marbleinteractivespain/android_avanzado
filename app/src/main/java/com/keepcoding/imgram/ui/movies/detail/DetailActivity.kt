package com.keepcoding.imgram.ui.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.keepcoding.imgram.databinding.ActivityDetailBinding
import com.keepcoding.imgram.model.presentation.MoviePresentation
import com.keepcoding.imgram.Properties

class DetailActivity : AppCompatActivity() {

    companion object{
       const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<MoviePresentation>(EXTRA_MOVIE)
        if(movie != null){

            title = movie.title

            binding.detailTitle.text = movie.original_title

            Glide.with(this)
                .load("${Properties.IMG_URL}${movie.posterPath}")
                .into(binding.backdrop)

            binding.overviewDetail.text = buildSpannedString {
                bold { append("Release Date: ") }
                appendLine(movie.releaseDate)

                bold { append("Original Language: ") }
                appendLine(movie.original_language)

                bold { append("Vote Average: ") }
                appendLine(movie.vote_average.toString())

                appendLine("")
                appendLine(movie.overview)

            }
        }
    }
}