package com.codepath.nationalparks

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from the TMDB API.
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the Gson library.
 */
class Movie {

    // Movie title
    @JvmField
    @SerializedName("title")
    var title: String? = null

    // Movie overview/description
    @JvmField
    @SerializedName("overview")
    var description: String? = null

    // Movie release date
    @JvmField
    @SerializedName("release_date")
    var releaseDate: String? = null

    // Poster path (partial URL)
    @JvmField
    @SerializedName("poster_path")
    var posterPath: String? = null

    // Convenience property to get full poster URL
    val posterUrl: String?
        get() = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }

    // Optional: vote average or rating
    @JvmField
    @SerializedName("vote_average")
    var rating: Double? = null
}