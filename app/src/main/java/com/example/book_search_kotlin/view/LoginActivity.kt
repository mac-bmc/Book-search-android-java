package com.example.book_search_kotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.book_search_kotlin.R
import com.example.book_search_kotlin.viewmodel.BookViewModel

class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val usernameText: EditText = findViewById(R.id.usernameText)
        val passwordText: EditText = findViewById(R.id.passwordText)
        val loginButton: Button = findViewById(R.id.loginButton)
        bookViewModel = ViewModelProvider(
            this,).get(BookViewModel::class.java)

        loginButton.setOnClickListener {
            val login = bookViewModel.Login()
            val key: Boolean =
                login.loginAuth(usernameText.text.toString(), passwordText.text.toString())
            if (key) {
                val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
                intent.putExtra("userName", usernameText.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "Login Failed check credentials",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

    }
}