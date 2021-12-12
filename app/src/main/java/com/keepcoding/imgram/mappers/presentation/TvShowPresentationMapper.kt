package com.keepcoding.imgram.mappers.presentation

import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.presentation.TvShowPresentation
import javax.inject.Inject

class TvShowPresentationMapper @Inject constructor() {

    fun mapDataToPresentation(data: List<TvShowItemData>): List<TvShowPresentation> {
        return data.map { mapDataToPresentation(it) }
    }

    fun mapDataToPresentation(data: TvShowItemData): TvShowPresentation {
        return TvShowPresentation(data.id, data.name, data.posterPath, data.overview, data.isFavorite)
    }

    fun mapLocalDataToPresentation(data: TvShowItemLocalData): TvShowPresentation {
        return  TvShowPresentation(data.id, data.name, data.posterPath, data.overview, data.isFovorite)
    }


    fun mapPresentationToData(presentation: TvShowPresentation): TvShowItemData {
        return TvShowItemData(presentation.id, presentation.name, presentation.posterPath, presentation.overview, presentation.isFavorite)
    }
}