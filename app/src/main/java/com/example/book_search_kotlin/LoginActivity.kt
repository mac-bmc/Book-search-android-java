package com.example.book_search_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameText : EditText = findViewById(R.id.usernameText)
        val passwordText : EditText = findViewById(R.id.passwordText)
        val loginButton  : Button = findViewById(R.id.loginbutton)

        loginButton.setOnClickListener(){
            val login = Login()
            val key: Boolean=login.LoginAuth(usernameText.text.toString(),passwordText.text.toString())
            if(key)
            {
                val intent =Intent(this@LoginActivity,WelcomeActivity::class.java)
                intent.putExtra("userName",usernameText.text.toString())
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this@LoginActivity,"Login Failed check credentials",Toast.LENGTH_SHORT).show()
            }


        }

    }
}