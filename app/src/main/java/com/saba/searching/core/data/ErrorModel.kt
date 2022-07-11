package com.saba.searching.core.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorModel(
    @SerializedName("error")
    val error: String = "",
    @SerializedName("Message")
    val message: String = "",
    @SerializedName("Status")
    val statusCode: Int = 0
)
