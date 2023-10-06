package com.example.book_search_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    fun loginAuth(username: String, password: String): Boolean {
        var key = false
        if (username.isNotEmpty() && password.isNotEmpty()) {
            key = true
        }
        return key
    }

}