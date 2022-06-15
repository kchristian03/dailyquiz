package com.vintech.testfinalproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.vintech.testfinalproject.apiRequest.PingRequest
import com.vintech.testfinalproject.helpers.RetrofitHelper
import com.vintech.testfinalproject.models.ApiHttpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        checkApiServer()



        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }

    private fun checkApiServer() {
        val pingApi = RetrofitHelper.jsonResponseInstance().create(PingRequest::class.java)

        pingApi.ping().enqueue(object : Callback<ApiHttpResponse<Array<String?>>> {
            override fun onResponse(
                call: Call<ApiHttpResponse<Array<String?>>>,
                response: Response<ApiHttpResponse<Array<String?>>>
            ) {
            }

            override fun onFailure(call: Call<ApiHttpResponse<Array<String?>>>, t: Throwable) {

            }
        })
    }
}