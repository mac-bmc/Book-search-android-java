package com.example.book_search_kotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.book_search_kotlin.controller.Login
import com.example.book_search_kotlin.R

class LoginActivity : AppCompatActivity(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val usernameText: EditText = findViewById(R.id.usernameText)
        val passwordText: EditText = findViewById(R.id.passwordText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val login = Login()
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