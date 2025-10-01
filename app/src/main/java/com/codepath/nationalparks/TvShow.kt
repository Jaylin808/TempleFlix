package com.codepath.nationalparks

import com.google.gson.annotations.SerializedName

class TvShow {
    @SerializedName("name")
    var title: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    val posterUrl: String?
        get() = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }

    @SerializedName("first_air_date")
    var firstAirDate: String? = null

    @SerializedName("vote_average")
    var rating: Double? = null

    @SerializedName("original_language")
    var language: String? = null
}