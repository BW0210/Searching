package com.saba.searching.view.main.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Attributes(
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
    @SerializedName("rate_enable")
    val rateEnable: Boolean,
    @SerializedName("descr")
    val descr: String,
    @SerializedName("cover")
    val cover: String? = "",
    @SerializedName("publish_date")
    val publishDate: String,
    @SerializedName("age_range")
    val ageRange: String,
    @SerializedName("pic")
    val pic: Pic,
    @SerializedName("rate_avrage")
    val rateAvrage: String,
    @SerializedName("avg_rate_label")
    val avgRateLabel: String,
    @SerializedName("pro_year")
    val proYear: String,
    @SerializedName("imdb_rate")
    val imdbRate: String,
    @SerializedName("categories")
    val categories: ArrayList<Category>,
    @SerializedName("duration")
    val duration: Duration,
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("subtitle")
    val subtitle: Subtitle,
    @SerializedName("director")
    val director: String,
)