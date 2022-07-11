package com.saba.searching.view.main.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Country(
    @SerializedName("country")
    val country: String,
    @SerializedName("country_en")
    val countryEn: String
)