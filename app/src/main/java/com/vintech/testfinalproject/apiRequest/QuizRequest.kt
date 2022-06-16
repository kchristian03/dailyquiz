package com.vintech.testfinalproject.apiRequest

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.models.Quiz
import retrofit2.Call
import retrofit2.http.GET

interface QuizRequest {
    @GET("quizzes")
    fun getQuizzes(): Call<ApiHttpResponse<JsonArray>>
}