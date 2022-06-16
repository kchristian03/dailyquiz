package com.vintech.testfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import com.vintech.testfinalproject.apiRequest.AuthenticationRequest
import com.vintech.testfinalproject.databinding.ActivityLoginBinding
import com.vintech.testfinalproject.databinding.ActivityRegisterBinding
import com.vintech.testfinalproject.helpers.RetrofitHelper
import com.vintech.testfinalproject.helpers.TokenStorage
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.overrides.FillsQuiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : FillsQuiz() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            registerUser()
        }

        binding.extButtonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun registerUser() {
        val authApi =
            RetrofitHelper.jsonResponseInstance().create(AuthenticationRequest::class.java);

        authApi.register(
            binding.nameInputRegister.editText!!.text.toString(),
            binding.emailInputRegister.editText!!.text.toString(),
            binding.passwordInputRegister.editText!!.text.toString()
        ).enqueue(object : Callback<ApiHttpResponse<JsonObject?>> {

            override fun onResponse(
                call: Call<ApiHttpResponse<JsonObject?>>,
                response: Response<ApiHttpResponse<JsonObject?>>
            ) {
                if (response.isSuccessful) {
                    val token = response.body()!!.data!!.get("token").toString()
                    TokenStorage.setAuthToken(baseContext, token)
                    checkIfTokenIsExpired()
                }
            }


            override fun onFailure(call: Call<ApiHttpResponse<JsonObject?>>, t: Throwable) {
                Log.e("Failure registering!", t.toString())
            }

        })
    }
}