package com.vintech.testfinalproject.overrides

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.vintech.testfinalproject.MainActivity
import com.vintech.testfinalproject.apiRequest.QuizRequest
import com.vintech.testfinalproject.helpers.CurrentStorage
import com.vintech.testfinalproject.helpers.RetrofitHelper
import com.vintech.testfinalproject.helpers.TokenStorage
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.models.Quiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class FillsQuiz() : AppCompatActivity() {
    open fun setAvailableQuizzes() {
        val quizzesApi = RetrofitHelper.authenticatedJsonResponseInstance(
            TokenStorage.getAuthToken(
                applicationContext
            )
        ).create(QuizRequest::class.java)

        quizzesApi.getQuizzes().enqueue(object : Callback<ApiHttpResponse<JsonArray>> {
            override fun onResponse(
                call: Call<ApiHttpResponse<JsonArray>>,
                response: Response<ApiHttpResponse<JsonArray>>
            ) {
                if (response.isSuccessful) {

                    val arr = response.body()!!.data[0].toString();

                    val typeToken = object : TypeToken<List<Quiz>>() {}.type
                    val quizzes = Gson().fromJson<List<Quiz>>(arr, typeToken)

                    for (quiz in quizzes) {
                        CurrentStorage.quizzes.add(quiz)
                    }

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<ApiHttpResponse<JsonArray>>, t: Throwable) {
                Log.e("Error adding quiz from API", t.toString())
            }

        })
    }
}