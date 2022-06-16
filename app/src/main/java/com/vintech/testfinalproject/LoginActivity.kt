package com.vintech.testfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import com.vintech.testfinalproject.apiRequest.AuthenticationRequest
import com.vintech.testfinalproject.databinding.ActivityLoginBinding
import com.vintech.testfinalproject.helpers.RetrofitHelper
import com.vintech.testfinalproject.helpers.TokenStorage
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.overrides.FillsQuiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : FillsQuiz() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            loginUser()
        }

        binding.extButtonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun loginUser() {
        val loginApi =
            RetrofitHelper.jsonResponseInstance().create(AuthenticationRequest::class.java)

        loginApi.login(
            binding.usernameInputLogin.editText!!.text.toString(),
            binding.passwordInputLogin.editText!!.text.toString()
        ).enqueue(object : Callback<ApiHttpResponse<JsonObject?>> {

            override fun onResponse(
                call: Call<ApiHttpResponse<JsonObject?>>,
                response: Response<ApiHttpResponse<JsonObject?>>
            ) {
                if (!response.isSuccessful) {
                    Snackbar
                        .make(binding.root, "Failed to login!", Snackbar.LENGTH_LONG)
                        .show()
                } else {
                    val res: String = response.body()!!.data!!.get("token").toString()
                    TokenStorage.setAuthToken(applicationContext, res)
                    checkIfTokenIsExpired()
                }
            }

            override fun onFailure(call: Call<ApiHttpResponse<JsonObject?>>, t: Throwable) {
                Snackbar
                    .make(binding.root, "Failed to login!", Snackbar.LENGTH_LONG)
                    .show()
            }

        })
    }


}