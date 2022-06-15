package com.vintech.testfinalproject.apiRequest

import com.google.gson.JsonObject
import com.vintech.testfinalproject.models.ApiHttpResponse
import com.vintech.testfinalproject.models.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationRequest {
    @FormUrlEncoded
    @POST("auth/create-token")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ApiHttpResponse<JsonObject?>>


    @GET("my")
    fun checkUser(): Call<ApiHttpResponse<User>>
}