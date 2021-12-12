package com.keepcoding.imgram.ui.tvshows

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.imgram.data.Repository
import com.keepcoding.imgram.mappers.presentation.TvShowPresentationMapper
import com.keepcoding.imgram.model.presentation.TvShowPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class TvShowViewModel @Inject constructor(private val repository: Repository, private val mapper: TvShowPresentationMapper) : ViewModel() {

    private val _images: MutableLiveData<List<TvShowPresentation>> by lazy {
        MutableLiveData<List<TvShowPresentation>>()
    }
    val images: LiveData<List<TvShowPresentation>> get() = _images


    private val _tvshow: MutableLiveData<TvShowPresentation> by lazy {
        MutableLiveData<TvShowPresentation>()
    }

    val tvshow: MutableLiveData<TvShowPresentation> get() = _tvshow

    private var coroutine: Job? = null

    init {
        getTvShows()
    }


    private fun getTvShows(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getTvShows()
            }

            val sortedResults = result.sortedBy { it.name }

            _images.postValue(mapper.mapDataToPresentation(sortedResults))
        }
    }

    fun deleteTvShow(itemPresentation: TvShowPresentation){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
//                repository.deleteTvShow(mapper.mapPresentationToData(itemPresentation))
                itemPresentation.id?.also {
                    repository.deleteTvShowById(it)
                }

                repository.getTvShows()
            }

            _images.postValue(mapper.mapDataToPresentation(result))
        }
    }

    fun deleteAllTvShow(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.deleteAllTvShows()
                repository.getTvShows()
            }

            _images.postValue(mapper.mapDataToPresentation(result))
        }
    }

}