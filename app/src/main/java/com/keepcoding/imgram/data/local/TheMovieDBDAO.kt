package com.keepcoding.imgram.data.local

import androidx.room.*
import com.keepcoding.imgram.model.TvShowItemLocalData

@Dao
interface TheMovieDBDAO {

    @Query("SELECT * FROM tv_shows order by name DESC")
    fun getTvShows(): List<TvShowItemLocalData>

    @Insert
    fun insertTvShow(item: TvShowItemLocalData): Long

    @Insert
    fun insertTvShows(items: List<TvShowItemLocalData>)

    @Delete
    fun deleteTvShow(item: TvShowItemLocalData)

    @Query("Delete from tv_shows where :itemId = id")
    fun deleteTvShowById(itemId: Long)

    @Query("Delete from tv_shows")
    fun deleteAllTvShows()

    @Query("UPDATE tv_shows SET favorite = :isFavorite where :itemId = id")
    fun updateFavoriteTvShow(itemId: Long, isFavorite: Boolean)

    @Query("SELECT * FROM tv_shows  where :itemId = id")
    fun getTvShowsById(itemId: Long): TvShowItemLocalData

    @Query("SELECT * FROM tv_shows where favorite = 1 order by name DESC")
    fun getTvShowsFavorites(): List<TvShowItemLocalData>


}