package com.vintech.testfinalproject.helpers

import com.vintech.testfinalproject.models.Quiz
import com.vintech.testfinalproject.models.User

object CurrentStorage {
    lateinit var user: User;
    val quizzes: ArrayList<Quiz> = ArrayList()
}