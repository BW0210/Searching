package com.saba.searching.view.main.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDetailsModel(
    @SerializedName("movie_id")
    val movieId: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("movie_title")
    val movieTitle: String,
    @SerializedName("movie_title_en")
    val movieTitleEn: String,
    @SerializedName("HD")
    val hd: Boolean,
    @SerializedName("descr")
    val descr: String,
    @SerializedName("cover")
    val cover: String? = "",
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("rate_avrage")
    val rateAvrage: String,
    @SerializedName("pro_year")
    val proYear: String,
    @SerializedName("imdb_rate")
    val imdbRate: String,
    @SerializedName("director")
    val director: String,
)