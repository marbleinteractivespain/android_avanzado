package com.keepcoding.imgram.data.remote

import com.keepcoding.imgram.model.PagedResultData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTvShows(): PagedResultData<TvShowItemNetworkData>

    @GET("/3/tv/{id}")
    suspend fun getTvShowDetail(@Path("id")  id: Long): TvShowItemNetworkData

    @GET("/3/tv/{tv_id}/recommendations")
    suspend fun getTvShowsRecomendations(@Path("tv_id")  id: Long): PagedResultData<TvShowItemNetworkData>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): PagedResultData<MovieItemNetworkData>


}