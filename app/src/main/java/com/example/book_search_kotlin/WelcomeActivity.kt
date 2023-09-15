package com.example.book_search_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    private val paramUsername = "userName"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val welcomeText: TextView=findViewById(R.id.home)
        val bookSearchBtn: Button=findViewById(R.id.bookSearchBtn)
        val logoutButton: Button=findViewById(R.id.logoutBtn)
        val username=intent.getStringExtra(paramUsername)
        welcomeText.text = getString(R.string.welcome)+""+username
        bookSearchBtn.setOnClickListener(){
            val intent = Intent(this@WelcomeActivity,BookSearchActivity::class.java)
            startActivity(intent)
        }
        logoutButton.setOnClickListener(){
            val intent=Intent(this@WelcomeActivity,
                LoginActivity::class.java)
            startActivity(intent)
        }

    }
}