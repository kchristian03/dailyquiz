package com.vintech.testfinalproject.apiRequest

import com.vintech.testfinalproject.models.ApiHttpResponse
import retrofit2.http.GET

interface PingRequest {
    @GET("/ping")
    fun ping(): retrofit2.Call<ApiHttpResponse<Array<Int>>>
}