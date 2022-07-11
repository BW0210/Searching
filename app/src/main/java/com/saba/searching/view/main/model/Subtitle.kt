package com.saba.searching.view.main.model



import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Subtitle(
    @SerializedName("enable")
    val enable: Boolean,
    @SerializedName("text")
    val text: String
)