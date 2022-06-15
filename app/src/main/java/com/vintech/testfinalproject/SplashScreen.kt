package com.vintech.testfinalproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.android.material.snackbar.Snackbar
import com.vintech.testfinalproject.apiRequest.PingRequest
import com.vintech.testfinalproject.helpers.RetrofitHelper
import com.vintech.testfinalproject.helpers.TokenStorage
import com.vintech.testfinalproject.models.ApiHttpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        if (TokenStorage.getAuthToken(applicationContext).equals("")) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}