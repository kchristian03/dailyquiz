package com.vintech.testfinalproject.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("skill_points")
    var skill_points: Int,

    @SerializedName("coins")
    var coins: Int,

    @SerializedName("is_admin")
    var is_admin: Int

)
