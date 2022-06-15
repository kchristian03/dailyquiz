package com.vintech.testfinalproject.models

import com.google.gson.annotations.SerializedName

data class ApiHttpResponse<T>(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: T
)