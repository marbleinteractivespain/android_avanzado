package com.keepcoding.imgram.model.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviePresentation(
    var id: Long?,
    var title: String?,
    var posterPath: String?,
    var popularity: Double?,
    var releaseDate: String?,
    val overview: String?,
    val original_title: String?,
    val original_language: String?,
    val vote_average: Double?
) : Parcelable