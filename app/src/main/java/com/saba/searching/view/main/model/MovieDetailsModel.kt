package com.saba.searching.view.main.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDetailsModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: Attributes?
)