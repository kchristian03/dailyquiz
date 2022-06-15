package com.vintech.testfinalproject.helpers

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
}