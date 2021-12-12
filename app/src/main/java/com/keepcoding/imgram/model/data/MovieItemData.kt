package com.keepcoding.imgram.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItemData(
    val id: Long?,
    val title: String?,
    val posterPath: String?,
    val popularity: Double?,
    val releaseDate: String?,
    val overview: String?,
    val original_title: String?,
    val original_language: String?,
    val vote_average: Double?


) : Parcelable