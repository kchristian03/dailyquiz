package com.vintech.testfinalproject.models

data class ApiHttpResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T
)