package com.keepcoding.imgram.model.data

data class TvShowItemData(
    var id: Long?,
    var name: String?,
    var posterPath: String?,
    var overview: String?,
    var isFavorite: Boolean? = false,
)