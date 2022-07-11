package com.saba.searching.view.main.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Pic(
    @SerializedName("movie_img_s")
    val movieImgS: String,
    @SerializedName("movie_img_m")
    val movieImgM: String,
    @SerializedName("movie_img_b")
    val movieImgB: String
)