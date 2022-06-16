package com.vintech.testfinalproject.apiRequest

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.models.QuestionAndUserAnswer
import com.vintech.testfinalproject.models.Quiz
import retrofit2.Call
import retrofit2.http.*

interface QuizRequest {
    @GET("quizzes")
    fun getQuizzes(): Call<ApiHttpResponse<JsonArray>>

    @POST("quizzes/{QUIZ_ID}/join")
    fun joinQuiz(@Path("QUIZ_ID") id: Int): Call<ApiHttpResponse<QuestionAndUserAnswer>>

    @FormUrlEncoded
    @POST("quizzes/{QUIZ_ID}/answer")
    fun answerQuiz(
        @Path("QUIZ_ID") id: Int,
        @Field("question_no") question_no: Int,
        @Field("answer_no") answer_no: Int
    ): Call<ApiHttpResponse<Array<String?>>>

    @FormUrlEncoded
    @POST("quizzes/{QUIZ_ID}/end")
    fun endQuiz(
        @Path("QUIZ_ID") id: Int,
    ): retrofit2.Call<ApiHttpResponse<Array<String?>>>

}