package com.vintech.testfinalproject.models

import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("max_coin")
    var max_coin: Int,

    @SerializedName("max_score")
    var max_score: Int
)
