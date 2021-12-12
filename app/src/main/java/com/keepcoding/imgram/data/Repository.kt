package com.keepcoding.imgram.data

import android.util.Log
import com.keepcoding.imgram.data.local.LocalDataSource
import com.keepcoding.imgram.data.remote.RemoteDataSource
import com.keepcoding.imgram.mappers.data.MovieDataMapper
import com.keepcoding.imgram.mappers.data.TvShowDataMapper
import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val tvShowDataMapper: TvShowDataMapper,
    private val movieDataMapper: MovieDataMapper
) {

    private suspend fun getRemoteTvShows(): List<TvShowItemNetworkData> {
        val remoteResult = remoteDataSource.getTopShows()
        Log.d("Repository", "Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getRemoteTvShowsRecomendations(id: Long): List<TvShowItemNetworkData> {
        val remoteResult = remoteDataSource.getTvShowsRecomendations(id)
        Log.d("Repository", "Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getRemoteTvShowById(id: Long): TvShowItemNetworkData {
        val remoteResult = remoteDataSource.getTvShowById(id)
        Log.d("Repository", "Resultados remotos DETAIL ID: ${remoteResult.id}")
        return remoteResult
    }

    private suspend fun getRemotePopularMovies(): List<MovieItemNetworkData> {
        val remoteResult = remoteDataSource.getPopularMovies()
        Log.d("Repository", "Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getLocalTvShows(): List<TvShowItemLocalData> {
        val localResult = localDataSource.getTvShows()
        Log.d("Repository", "Resultados locales: ${localResult.size}")
        return localResult
    }

    suspend fun getTvShows(): List<TvShowItemData> {
        val result = arrayListOf<TvShowItemData>()
        result.addAll(tvShowDataMapper.mapLocalToData(getLocalTvShows()))

        if (result.isEmpty()) {
            val remote = tvShowDataMapper.mapNetworkToData(getRemoteTvShows())
            localDataSource.insertTvShows(tvShowDataMapper.mapDataToLocal(remote))
            result.addAll(tvShowDataMapper.mapLocalToData(getLocalTvShows()))
        }

        return result
    }

    suspend fun getTvShowsRecomendations(id: Long): List<TvShowItemData>{
        val result = arrayListOf<TvShowItemData>()
        val remote = tvShowDataMapper.mapNetworkToData(getRemoteTvShowsRecomendations(id))
        result.addAll(remote)

        return result
    }

    suspend fun getPopularMovies(): List<MovieItemData> {
        val result = arrayListOf<MovieItemData>()
        val remote = movieDataMapper.mapNetworkToData(getRemotePopularMovies())
        result.addAll(remote)

        return result
    }

    suspend fun deleteTvShow(itemData: TvShowItemData) {
        localDataSource.deleteTvShow(tvShowDataMapper.mapDataToLocal(itemData))
    }

    suspend fun deleteTvShowById(id: Long) {
        localDataSource.deleteTvShowById(id)
    }

    suspend fun deleteAllTvShows() {
        localDataSource.deleteAllTvShowS()
    }

    suspend fun getTvShowDetail(id: Long): TvShowItemData {
        //de network a local
        return tvShowDataMapper.mapNetworkToData(getRemoteTvShowById(id))
    }

     suspend fun getLocalTvShowsById(id:Long ): TvShowItemLocalData {
        val localResult = localDataSource.getTvShowsById(id)
        Log.d("Repository", "Resultados locales: ${localResult}")
        return localResult
    }

    suspend fun updateFavoriteTvShow(id: Long, isFavorite: Boolean) {
        localDataSource.updateFavoriteTvShow(id, isFavorite)
    }




    private suspend fun getLocalTvShowsFavorite(): List<TvShowItemLocalData> {
        val localResult = localDataSource.getTvShowsFavorites()
        Log.d("Repository", "Resultados locales: ${localResult.size}")
        return localResult
    }

    suspend fun getTvShowsFavorite(): List<TvShowItemData> {
        val result = arrayListOf<TvShowItemData>()
        result.addAll(tvShowDataMapper.mapLocalToData(getLocalTvShowsFavorite()))

        return result
    }
}



