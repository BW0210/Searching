package com.saba.searching.view.main.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Duration(
    @SerializedName("value")
    val value: Int,
    @SerializedName("text")
    val text: String
)