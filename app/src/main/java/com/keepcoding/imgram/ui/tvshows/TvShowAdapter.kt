package com.keepcoding.imgram.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.ItemListBinding
import com.keepcoding.imgram.model.presentation.TvShowPresentation

class TvShowAdapter(
    private val clickListener: (TvShowPresentation) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ImageViewHolder>() {

    var data = mutableListOf<TvShowPresentation>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(items: List<TvShowPresentation>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val binding = ItemListBinding.bind(itemView)
        private lateinit var item: TvShowPresentation
        var like = false

        init {
            binding.image.setOnClickListener {
                clickListener(item)
            }
        }

        fun bind(item: TvShowPresentation) {
            this.item = item
            with(binding) {
                imageTitle.text = item.name
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${item.posterPath}")
                    .placeholder(ContextCompat.getDrawable(itemView.context, R.mipmap.ic_launcher))
                    .into(image)
            }

            if (item.isFavorite == true)
                like = likeAnimation(binding.favoriteIcon, R.raw.bandai_dokkan, like)

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
}