package com.saba.searching.view.main.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDataResponseModel (
    @SerializedName("data")
    val data: ArrayList<Attributes>
)