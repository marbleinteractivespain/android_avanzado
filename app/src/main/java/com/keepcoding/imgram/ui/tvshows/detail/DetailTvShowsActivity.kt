package com.keepcoding.imgram.ui.tvshows.detail


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.keepcoding.imgram.Properties
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ActivityDetailTvShowsBinding
import com.keepcoding.imgram.model.presentation.TvShowPresentation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowsActivity : AppCompatActivity() {

    private val viewModel: TvShowDetailViewModel by viewModels()

    companion object{
        const val EXTRA_ID = "DetailTvShowsActivity:id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding  = ActivityDetailTvShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getLongExtra(EXTRA_ID,-1)

        var like = false
        binding.likeImageView.setVisibility(View.GONE);

        viewModel.corrutineTvShows(id)

        viewModel.tvshow.observe(this) {
            title = it.name
            binding.titleOurRecommendation.text= "Our Recommendation"

            var item: TvShowPresentation = it


            if (it.isFavorite == true) {
                binding.favotiteButton.text = "REMOVE FAVORITE"
            }else{
                binding.favotiteButton.text = "ADD FAVORITE"
            }

            binding.favotiteButton.setOnClickListener{

                viewModel.updateFavoriteTvShow(item)

                binding.favotiteButton.setVisibility(View.GONE);
                binding.likeImageView.setVisibility(View.VISIBLE);

                if(item.isFavorite == false) {
                    like = likeAnimation(binding.likeImageView, R.raw.bandai_dokkan, like)
                }
            }

            with(binding){
                titleTvShow.text = it.name
                summary.text = it.overview
            }

            Glide.with(this)
                .load("${Properties.IMG_URL}${it.posterPath}")
                .into(binding.cover)
        }

        //RECOMMENDATION
        viewModel.tvshowRecommendation.observe(this) {

            with(binding){
               titleRecomendation.text = it[0].name
                overview.text = it[0].overview
            }

            Glide.with(this)
                .load("${Properties.IMG_URL}${it[0].posterPath}")
                .into(binding.coverRecommendation)
        }

    }

    private fun likeAnimation(imageView: LottieAnimationView,
                              animation: Int,
                              like: Boolean) : Boolean {

        if (!like) {
            imageView.setAnimation(animation)
            imageView.playAnimation()
        } else {
            imageView.animate()
                .alpha(0f)
                .setDuration(200)
                .setListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animator: Animator) {

                        imageView.setImageResource(R.drawable.twitter_like)
                        imageView.alpha = 1f
                    }

                })

        }

        return !like
    }
}
