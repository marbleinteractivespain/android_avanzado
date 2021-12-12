package com.keepcoding.imgram.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.keepcoding.imgram.R
import com.keepcoding.imgram.databinding.FragmentMainBinding
import com.keepcoding.imgram.ui.MainActivity
import com.keepcoding.imgram.ui.TvShowAdapter
import com.keepcoding.imgram.ui.commons.viewBinding
import com.keepcoding.imgram.ui.tvshows.detail.DetailTvShowsActivity
import com.keepcoding.imgram.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: TvShowViewModel by viewModels()

    private val imageAdapter = TvShowAdapter {
        val id = it.id?.toLong()

      //  viewModel.updateFavoriteTvShow(it)

        if(id != null){
            navigateTo(id)
        }
    }

    private fun navigateTo(idTvShow: Long) {
            val intent = Intent(context, DetailTvShowsActivity::class.java)
            intent.putExtra(DetailTvShowsActivity.EXTRA_ID, idTvShow)
            startActivity(intent)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "Tv Shows"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            imageList.adapter = imageAdapter
            imageList.layoutManager = GridLayoutManager(context, 2)
            progress.visible(true)
        }

        viewModel.images.observe(this) {
            imageAdapter.addAll(it)

            binding.progress.visible(false)
        }
    }
}