package com.vintech.testfinalproject.overrides

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.vintech.testfinalproject.LoginActivity
import com.vintech.testfinalproject.MainActivity
import com.vintech.testfinalproject.apiRequest.AuthenticationRequest
import com.vintech.testfinalproject.apiRequest.QuizRequest
import com.vintech.testfinalproject.helpers.CurrentStorage
import com.vintech.testfinalproject.helpers.RetrofitHelper
import com.vintech.testfinalproject.helpers.TokenStorage
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.models.Quiz
import com.vintech.testfinalproject.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class FillsQuiz : AppCompatActivity() {
    open fun checkIfTokenIsExpired() {
        val tokenCheckApi =
            RetrofitHelper.authenticatedJsonResponseInstance(
                TokenStorage.getAuthToken(
                    applicationContext
                )
            ).create(AuthenticationRequest::class.java)

        tokenCheckApi.checkUser()
            .enqueue(object : Callback<ApiHttpResponse<User?>> {
                /**
                 * Invoked for a received HTTP response.
                 *
                 *
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call [Response.isSuccessful] to determine if the response indicates success.
                 */
                override fun onResponse(
                    call: Call<ApiHttpResponse<User?>>,
                    response: Response<ApiHttpResponse<User?>>
                ) {
                    CurrentStorage.user = response.body()!!.data!!
                    setAvailableQuizzes()
                }

                /**
                 * Invoked when a network exception occurred talking to the server or when an unexpected exception
                 * occurred creating the request or processing the response.
                 */
                override fun onFailure(call: Call<ApiHttpResponse<User?>>, t: Throwable) {
                    val intent = Intent(baseContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            })
    }

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