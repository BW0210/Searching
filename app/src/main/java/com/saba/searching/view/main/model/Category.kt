package com.saba.searching.view.main.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Category(
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("link_key")
    val linkKey: String,
    @SerializedName("link_type")
    val linkType: String
)