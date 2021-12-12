package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import javax.inject.Inject

class MovieDataMapper @Inject constructor() {

    private fun mapNetworkToData(network: MovieItemNetworkData): MovieItemData {
        return MovieItemData(
            network.id,
            network.title,
            network.posterPath,
            network.popularity,
            network.releaseDate,
            network.overview,
            network.original_title,
            network.original_language,
            network.vote_average
        )
    }

    fun mapNetworkToData(networkList: List<MovieItemNetworkData>): List<MovieItemData> {
        return networkList.map { mapNetworkToData(it) }
    }
}