package com.vintech.testfinalproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import com.vintech.testfinalproject.apiRequest.AuthenticationRequest
import com.vintech.testfinalproject.apiRequest.PingRequest
import com.vintech.testfinalproject.helpers.RetrofitHelper
import com.vintech.testfinalproject.helpers.TokenStorage
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        checkApiServer()

    }

    // Checks if API Server is online
    private fun checkApiServer() {
        val pingApi = RetrofitHelper.jsonResponseInstance().create(PingRequest::class.java)

        pingApi.ping().enqueue(object : Callback<ApiHttpResponse<Array<String?>>> {
            override fun onResponse(
                call: Call<ApiHttpResponse<Array<String?>>>,
                response: Response<ApiHttpResponse<Array<String?>>>
            ) {
                if (response.body()?.success == true) checkToken()
            }

            override fun onFailure(call: Call<ApiHttpResponse<Array<String?>>>, t: Throwable) {
                Snackbar
                    .make(
                        View(this@SplashScreen),
                        "Failed to connect to game server!",
                        Snackbar.LENGTH_LONG
                    )
                    .show()
            }
        })
    }

    // Check if token is already in sharedPreference
    private fun checkToken() {

        if (TokenStorage.getAuthToken(applicationContext) == "") {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        checkIfTokenIsExpired()

    }

    private fun checkIfTokenIsExpired() {
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
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
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
}