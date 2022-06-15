package com.vintech.testfinalproject.helpers

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitHelper {


    fun stringResponseInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.API_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    fun jsonResponseInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun authenticatedJsonResponseInstance(token: String): Retrofit {

        val okhttp = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer $token")
                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()


        return Retrofit.Builder()
            .baseUrl(AppConfig.API_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}