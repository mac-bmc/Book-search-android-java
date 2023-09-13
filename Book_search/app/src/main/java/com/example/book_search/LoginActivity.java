package com.example.book_search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username =  findViewById(R.id.usrnametext);
        EditText password =  findViewById(R.id.passtext);

        Button loginbutton =  findViewById(R.id.Signinbtn);

        loginbutton.setOnClickListener(view -> {

            if(username.getText().toString().trim().length()==0||password.getText().toString().trim().length()==0)
            {
                Toast.makeText(LoginActivity.this, getString(R.string.login_failed),
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(LoginActivity.this, getString(R.string.login_successful),Toast.LENGTH_SHORT).show();
                String uname=username.getText().toString();
                Intent intent = new Intent(LoginActivity.this,WelcomeUserActivity.class);
                intent.putExtra(getString(R.string.username),uname);
                startActivity(intent);
            }

        });
    }
}