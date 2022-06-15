package com.vintech.testfinalproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Toast
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
        val pingApiServer = RetrofitHelper.jsonResponseInstance().create(PingRequest::class.java)

        pingApiServer.ping().enqueue(object : Callback<ApiHttpResponse<Array<Int>>> {
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<ApiHttpResponse<Array<Int>>>,
                response: Response<ApiHttpResponse<Array<Int>>>
            ) {
                Toast.makeText(baseContext, "Success sarimi isi 2", Toast.LENGTH_LONG).show()
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<ApiHttpResponse<Array<Int>>>, t: Throwable) {
                Toast.makeText(baseContext, "Failed sarimi isi 2", Toast.LENGTH_LONG).show()
            }

        })
    }
}