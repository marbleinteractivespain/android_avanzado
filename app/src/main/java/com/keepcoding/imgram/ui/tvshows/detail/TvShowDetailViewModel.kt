package com.keepcoding.imgram.ui.tvshows.detail

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
class TvShowDetailViewModel @Inject constructor(private val repository: Repository, private val mapper: TvShowPresentationMapper) : ViewModel() {

    private val _tvshow: MutableLiveData<TvShowPresentation> by lazy {
        MutableLiveData<TvShowPresentation>()
    }
    val tvshow: MutableLiveData<TvShowPresentation> get() = _tvshow


    private val _tvshowRecommendation: MutableLiveData<List<TvShowPresentation>> by lazy {
        MutableLiveData<List<TvShowPresentation>>()
    }
    val tvshowRecommendation: LiveData<List<TvShowPresentation>> get() = _tvshowRecommendation



    fun corrutineTvShows(id: Long){
        viewModelScope.launch {

            val resultDetail = async(Dispatchers.IO){
                repository.getLocalTvShowsById(id)
            }

            val resultRecommendation = async(Dispatchers.IO){
                repository.getTvShowsRecomendations(id)
            }

            if(resultDetail.await() != null && resultRecommendation != null){
                _tvshow.postValue(mapper.mapLocalDataToPresentation(resultDetail.await()))
                _tvshowRecommendation.postValue(mapper.mapDataToPresentation(resultRecommendation.await()))
            }

        }
    }


    fun updateFavoriteTvShow(itemPresentation: TvShowPresentation){
        var fav = itemPresentation.isFavorite
        fav = fav != true

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                itemPresentation.id?.also {
                    repository.updateFavoriteTvShow(it, fav)
                }
            }

        }
    }

    //**** ESTAS DOS FUNCIONES YA NO LAS USO, PUESTO QUE CREE LA DE ARRIBA CON EL ASYNC
    fun getTvShowDetails(id: Long){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getTvShowDetail(id)
            }
           _tvshow.postValue(mapper.mapDataToPresentation(result))
        }
    }

    fun getTvShowsRecomendations(id: Long){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getTvShowsRecomendations(id)
            }
            _tvshowRecommendation.postValue(mapper.mapDataToPresentation(result))
        }
    }
    //****** END - FUNCIONES NO USADAS
}