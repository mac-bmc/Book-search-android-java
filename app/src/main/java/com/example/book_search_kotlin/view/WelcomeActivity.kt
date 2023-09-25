package com.example.book_search_kotlin.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.book_search_kotlin.R

class WelcomeActivity : AppCompatActivity(R.layout.activity_welcome) {
    private val paramUsername = "userName"
    private var welcome="Welcome "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val welcomeText: TextView=findViewById(R.id.home)
        val bookSearchBtn: Button=findViewById(R.id.bookSearchBtn)
        val logoutButton: Button=findViewById(R.id.logoutBtn)
        val username=intent.getStringExtra(paramUsername)
        welcome += username
        welcomeText.text = welcome
        bookSearchBtn.setOnClickListener{
            val intent = Intent(this@WelcomeActivity, BookSearchActivity::class.java)
            startActivity(intent)
        }
        logoutButton.setOnClickListener{
            val intent=Intent(this@WelcomeActivity,
                LoginActivity::class.java)
            startActivity(intent)
        }

    }
}