package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import javax.inject.Inject

class TvShowDataMapper @Inject constructor() {

    private fun mapLocalToData(local: TvShowItemLocalData): TvShowItemData {
        return TvShowItemData(local.id, local.name, local.posterPath, local.overview, local.isFovorite)
    }

    fun mapLocalToData(localList: List<TvShowItemLocalData>): List<TvShowItemData> {
        return localList.map { mapLocalToData(it) }
    }

    fun mapDataToLocal(local: TvShowItemData): TvShowItemLocalData {
        return TvShowItemLocalData(local.id, local.name, local.posterPath, local.overview, local.isFavorite)
    }

    fun mapDataToLocal(localList: List<TvShowItemData>): List<TvShowItemLocalData> {
        return localList.map { mapDataToLocal(it) }
    }

    fun mapNetworkToData(local: TvShowItemNetworkData): TvShowItemData {
        return TvShowItemData(local.id, local.name, local.posterPath, local.overview)
    }

    fun mapNetworkToData(localList: List<TvShowItemNetworkData>): List<TvShowItemData> {
        return localList.map { mapNetworkToData(it) }
    }
}