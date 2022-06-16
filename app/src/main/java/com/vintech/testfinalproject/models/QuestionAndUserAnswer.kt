package com.vintech.testfinalproject.models

import com.google.gson.annotations.SerializedName

data class QuestionAndUserAnswer(
    @SerializedName("answers")
    var answers: ArrayList<Int?>,
    var questions: ArrayList<QuestionAndAnswer> = ArrayList()
)
