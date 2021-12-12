package com.keepcoding.imgram.data.remote

import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: TheMovieDBApi) {

    //TV SHOWS
    suspend fun getTopShows(): List<TvShowItemNetworkData> {
        val pagedResultData = api.getTopRatedTvShows()
        return pagedResultData.results
    }

    suspend fun getTvShowById(id: Long): TvShowItemNetworkData{
        val dataResult = api.getTvShowDetail(id)
        return dataResult
    }


    suspend fun getTvShowsRecomendations(id: Long): List<TvShowItemNetworkData> {
        val pagedResultData = api.getTvShowsRecomendations(id)
        return pagedResultData.results
    }


    //MOVIES
    suspend fun getPopularMovies(): List<MovieItemNetworkData> {
        val pagedResultData = api.getPopularMovies()
        return pagedResultData.results
    }
}

