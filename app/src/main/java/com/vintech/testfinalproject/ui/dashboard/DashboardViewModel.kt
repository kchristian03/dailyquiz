package com.vintech.testfinalproject.ui.dashboard

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vintech.testfinalproject.databinding.ActivityLoginBinding
import com.vintech.testfinalproject.databinding.FragmentDashboardBinding

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text






}