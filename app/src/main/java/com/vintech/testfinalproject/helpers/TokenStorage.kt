package com.vintech.testfinalproject.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.vintech.testfinalproject.SplashScreen

object TokenStorage {
    fun getAuthToken(context: Context): String {
        val pref: SharedPreferences =
            context.getSharedPreferences(AppConfig.SHAREDPREFERENCE_TOKEN_NAME, 0)
        return pref.getString(AppConfig.TOKEN_NAME, "") ?: "";
    }

    @SuppressLint("CommitPrefEdits")
    fun setAuthToken(context: Context, token: String) {
        val pref: SharedPreferences =
            context.getSharedPreferences(AppConfig.SHAREDPREFERENCE_TOKEN_NAME, 0)
        val editor = pref.edit()
        editor.putString(AppConfig.TOKEN_NAME, token)
        editor.apply()
    }
}